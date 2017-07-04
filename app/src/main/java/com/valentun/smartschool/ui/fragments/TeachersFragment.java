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

public class TeachersFragment extends BaseRecyclerFragment {

    public static TeachersFragment newInstance() {
        TeachersFragment fragment = new TeachersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle(getString(R.string.teachers_fragment_title));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        makeRequest();
    }

    // TODO Replace with db request
    private void makeRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<NamedEntity> teachers = FakeDataUtils.generateTeachers();
                initRecyclerView(new NamedEntityAdapter(teachers));
                showRecyclerView();
            }
        }, 1000);
    }
}
