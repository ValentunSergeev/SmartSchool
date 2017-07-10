package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.GroupsListAdapter;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.ArrayList;

public class GroupsListFragment extends BaseRecyclerFragment {

    private ArrayList<Group> groups;

    public static GroupsListFragment newInstance() {
        GroupsListFragment fragment = new GroupsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().setTitle(getString(R.string.groups_fragment_title));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (groups == null) {
            makeRequest();
        } else {
            initRecyclerView(new GroupsListAdapter(groups, getFragmentManager()));
            showRecyclerView();
        }
    }

    // TODO Replace with db request
    private void makeRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                groups = FakeDataUtils.allGroups;
                initRecyclerView(new GroupsListAdapter(groups, getFragmentManager()));
                showRecyclerView();
            }
        }, 1000);
    }

}
