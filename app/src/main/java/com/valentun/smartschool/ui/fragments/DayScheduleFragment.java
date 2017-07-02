package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.valentun.smartschool.DTO.Lesson;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.DayAdapter;
import com.valentun.smartschool.utils.FakeDataUtils;
import com.valentun.smartschool.utils.PreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayScheduleFragment extends Fragment {

    public static final String DAY_OF_WEEK_KEY = "weekDay";

    private static long groupId = PreferenceUtils.DEFAULT_ID_VALUE;

    @BindView(R.id.day_progress)
    ProgressBar progressBar;
    @BindView(R.id.day_list)
    RecyclerView recyclerView;

    private int weekDay;
    private ArrayList<Lesson> lessons;

    public static DayScheduleFragment newInstance(int dayOfWeek) {
        DayScheduleFragment fragment = new DayScheduleFragment();

        Bundle args = new Bundle();
        args.putInt(DAY_OF_WEEK_KEY, dayOfWeek);

        fragment.setArguments(args);
        return fragment;
    }

    public static void setGroupId(long groupId) {
        DayScheduleFragment.groupId = groupId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weekDay = getArguments().getInt(DAY_OF_WEEK_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DayAdapter(lessons));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && lessons == null) {
            makeRequest();
        }
    }

    // TODO replace with retrofit's request
    private void makeRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lessons = FakeDataUtils.generateLessons();
                initRecyclerView();
                progressBar.setVisibility(View.GONE);
            }
        }, 1000);
    }
}
