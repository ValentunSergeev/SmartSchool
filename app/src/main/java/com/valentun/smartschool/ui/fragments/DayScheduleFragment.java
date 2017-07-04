package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.os.Handler;

import com.valentun.smartschool.DTO.Lesson;
import com.valentun.smartschool.adapters.DayAdapter;
import com.valentun.smartschool.utils.FakeDataUtils;
import com.valentun.smartschool.utils.PreferenceUtils;

import java.util.ArrayList;

public class DayScheduleFragment extends BaseRecyclerFragment {

    public static final String DAY_OF_WEEK_KEY = "weekDay";

    private static long groupId = PreferenceUtils.DEFAULT_ID_VALUE;
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
                initRecyclerView(new DayAdapter(lessons));
                showRecyclerView();
            }
        }, 1000);
    }
}
