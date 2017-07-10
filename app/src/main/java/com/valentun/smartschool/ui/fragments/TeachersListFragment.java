package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.valentun.smartschool.DTO.Teacher;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.TeachersListAdapter;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.ArrayList;

public class TeachersListFragment extends BaseRecyclerFragment {

    private ArrayList<Teacher> teachers;

    public static TeachersListFragment newInstance() {
        TeachersListFragment fragment = new TeachersListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().setTitle(getString(R.string.teachers_fragment_title));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (teachers == null) {
            makeRequest();
        } else {
            initRecyclerView(new TeachersListAdapter(getFragmentManager(), teachers));
            showRecyclerView();
        }
    }

    // TODO Replace with db request
    private void makeRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                teachers = FakeDataUtils.allTeachers;
                initRecyclerView(new TeachersListAdapter(getFragmentManager(), teachers));
                showRecyclerView();
            }
        }, 1000);
    }
}
