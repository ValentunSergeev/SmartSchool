package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.GroupSearchAdapter;
import com.valentun.smartschool.listeners.SearchListener;
import com.valentun.smartschool.utils.FakeDataUtils;
import com.valentun.smartschool.utils.PreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class MyScheduleFragment extends GroupDetailFragment implements GroupSearchAdapter.ItemClickCallback {

    @BindView(R.id.select_group_container) LinearLayout selectGroupContainer;
    @BindView(R.id.search_group_progress) ProgressBar progressBar;
    @BindView(R.id.search_group_list) RecyclerView recyclerView;

    private MenuItem changeGroupItem, searchGroupItem;
    private SearchView searchView;
    private GroupSearchAdapter adapter;

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
        if(object == null) tabLayout.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_schedule_menu, menu);
        changeGroupItem = menu.findItem(R.id.action_change_group);
        searchGroupItem = menu.findItem(R.id.search);

        boolean isChangeItemVisible = PreferenceUtils.isGroupSelected(activity);
        changeGroupItem.setVisible(isChangeItemVisible);
        searchGroupItem.setVisible(!isChangeItemVisible);

        searchView = (SearchView) MenuItemCompat.getActionView(searchGroupItem);
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

    private void showGroupSelectionView() {
        object = null;
        recyclerView.setAdapter(null);

        activity.setTitle(getString(R.string.select_group_title));

        tabLayout.setVisibility(View.GONE);
        pager.setVisibility(View.GONE);
        selectGroupContainer.setVisibility(View.VISIBLE);
        if(changeGroupItem != null) changeGroupItem.setVisible(false);

        makeGroupRequest();
    }

    private void makeGroupRequest() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initializeSearchViews(FakeDataUtils.allGroups);
            }
        }, 1000);
    }

    private void initializeSearchViews(ArrayList<Group> data) {
        adapter = new GroupSearchAdapter(data, this);
        SearchListener listener = new SearchListener(adapter, recyclerView, progressBar);

        searchView.setOnQueryTextListener(listener);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        recyclerView.setVisibility(View.VISIBLE);
        searchGroupItem.setVisible(true);
        progressBar.setVisibility(GONE);
    }


    protected void initialize() {
        tabLayout.setVisibility(View.VISIBLE);
        pager.setVisibility(View.VISIBLE);
        selectGroupContainer.setVisibility(View.GONE);
        if(searchGroupItem != null) searchGroupItem.setVisible(false);
        if(changeGroupItem != null) changeGroupItem.setVisible(true);

        super.initialize();
    }


    @Override
    public void onItemClicked(Group item) {
        object = item;
        MenuItemCompat.collapseActionView(searchGroupItem);
        PreferenceUtils.setSelectedGroup(activity, object.getId());
        initialize();
    }

}
