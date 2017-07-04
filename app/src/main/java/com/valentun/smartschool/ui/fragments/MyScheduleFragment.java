package com.valentun.smartschool.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.valentun.smartschool.DTO.NamedEntity;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.GroupAutocompleteAdapter;
import com.valentun.smartschool.adapters.MyScheduleSlideAdapter;
import com.valentun.smartschool.ui.views.DelayAutoCompleteTextView;
import com.valentun.smartschool.utils.DateUtils;
import com.valentun.smartschool.utils.PreferenceUtils;
import com.valentun.smartschool.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyScheduleFragment extends Fragment {

    @BindView(R.id.viewpager) ViewPager pager;
    @BindView(R.id.select_group_container) ConstraintLayout selectGroupContainer;

    @BindView(R.id.autocomplete_chooser_view) DelayAutoCompleteTextView groupChooser;
    @BindView(R.id.autocomplete_progress_bar) ProgressBar progressBar;

    private TabLayout tabLayout;
    private NamedEntity group;
    private Context context;

    public static MyScheduleFragment newInstance() {
        MyScheduleFragment fragment = new MyScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        setHasOptionsMenu(true);
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tabLayout.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_schedule_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_group) {
            PreferenceUtils.deleteSelectedGroup(context);
            showGroupSelectionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        if(PreferenceUtils.isGroupSelected(context)) {
            DayScheduleFragment.setGroupId(PreferenceUtils.getSelectedGroup(context));
            showViewPager();
        } else {
            showGroupSelectionView();
        }
    }

    @OnClick(R.id.select_group_button)
    public void onSelectGroupButtonClicked(View view) {
        if (group == null) {
            Snackbar.make(selectGroupContainer,
                    getString(R.string.empty_group_message), Snackbar.LENGTH_LONG)
                    .show();
        } else {
            UIUtils.hideKeyboard(getActivity());
            PreferenceUtils.setSelectedGroup(context, group.getId());
            DayScheduleFragment.setGroupId(PreferenceUtils.getSelectedGroup(context));
            showViewPager();
        }
    }

    private void showGroupSelectionView() {
        initializeAutoCompleteView();
        group = null;

        tabLayout.setVisibility(View.GONE);
        pager.setVisibility(View.GONE);
        selectGroupContainer.setVisibility(View.VISIBLE);
    }

    private void showViewPager() {
        tabLayout.setVisibility(View.VISIBLE);
        pager.setVisibility(View.VISIBLE);
        selectGroupContainer.setVisibility(View.GONE);

        MyScheduleSlideAdapter adapter = new MyScheduleSlideAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(7);
        tabLayout.setupWithViewPager(pager);

        pager.setCurrentItem(DateUtils.ViewPagerUtils.getCurrentDayPosition());
    }

    private void initializeAutoCompleteView() {
        groupChooser.getText().clear();
        groupChooser.setThreshold(1);
        groupChooser.setAdapter(new GroupAutocompleteAdapter(context));
        groupChooser.setLoadingIndicator(progressBar);
        groupChooser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                NamedEntity selectedNamedEntity = (NamedEntity) adapterView.getItemAtPosition(position);
                groupChooser.setText(selectedNamedEntity.getName());
                group = selectedNamedEntity;
            }
        });
    }

}
