package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.valentun.smartschool.DTO.NamedEntity;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.NamedEntityAdapter;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.ArrayList;

public class GroupsFragment extends BaseRecyclerFragment {

    public static GroupsFragment newInstance() {
        GroupsFragment fragment = new GroupsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle(getString(R.string.groups_fragment_title));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        makeRequest();
    }

    // TODO Replace with db request
    private void makeRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<NamedEntity> groups = FakeDataUtils.allGroups;
                initRecyclerView(new NamedEntityAdapter(groups));
                showRecyclerView();
            }
        }, 1000);
    }

}
