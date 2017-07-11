package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.valentun.smartschool.Constants;
import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.GroupAutocompleteAdapter;
import com.valentun.smartschool.ui.views.DelayAutoCompleteTextView;
import com.valentun.smartschool.utils.FakeDataUtils;
import com.valentun.smartschool.utils.PreferenceUtils;
import com.valentun.smartschool.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyScheduleFragment extends GroupDetailFragment {

    @BindView(R.id.select_group_container)
    ConstraintLayout selectGroupContainer;

    @BindView(R.id.autocomplete_chooser_view)
    DelayAutoCompleteTextView groupChooser;
    @BindView(R.id.autocomplete_progress_bar)
    ProgressBar progressBar;

    public static MyScheduleFragment newInstance() {
        return new MyScheduleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        activity.setDefaultHomeState();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_schedule_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_group) {
            PreferenceUtils.deleteSelectedGroup(activity);
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

        if (object != null) {
            initialize();
        } else {
            showGroupSelectionView();
        }
    }

    @Override
    protected void setObjectFromExtras() {
        long selectedGroupId = PreferenceUtils.getSelectedGroup(activity);
        object = FakeDataUtils.findGroupById(selectedGroupId);
    }

    @OnClick(R.id.select_group_button)
    public void onSelectGroupButtonClicked(View view) {
        UIUtils.hideKeyboard(getActivity());
        if (object != null) {
            PreferenceUtils.setSelectedGroup(activity, object.getId());
            initialize();
        } else {
            String text = groupChooser.getText().toString().toLowerCase();
            String message = null;

            if (text.contains(Constants.EASTER1_TRIGGER)) message = getString(R.string.easter_string_1);
            if (text.contains(Constants.EASTER_2_TRIGGER)) message = getString(R.string.easter_string_2);

            if (message == null) message = getString(R.string.empty_group_message);

            Snackbar.make(selectGroupContainer, message, Snackbar.LENGTH_LONG).show();
        }
    }

    private void showGroupSelectionView() {
        initializeAutoCompleteView();
        object = null;

        tabLayout.setVisibility(View.GONE);
        pager.setVisibility(View.GONE);
        selectGroupContainer.setVisibility(View.VISIBLE);
    }


    protected void initialize() {
        tabLayout.setVisibility(View.VISIBLE);
        pager.setVisibility(View.VISIBLE);
        selectGroupContainer.setVisibility(View.GONE);

        super.initialize();
    }

    private void initializeAutoCompleteView() {
        groupChooser.getText().clear();
        groupChooser.setThreshold(1);
        groupChooser.setAdapter(new GroupAutocompleteAdapter(activity));
        groupChooser.setLoadingIndicator(progressBar);
        groupChooser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Group group = (Group) adapterView.getItemAtPosition(position);
                groupChooser.setText(group.getName());
                object = group;
            }
        });
    }

}
