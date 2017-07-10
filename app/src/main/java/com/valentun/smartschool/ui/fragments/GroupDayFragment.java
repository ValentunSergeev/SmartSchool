package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.os.Handler;

import com.valentun.smartschool.DTO.Lesson;
import com.valentun.smartschool.adapters.DayAdapter;
import com.valentun.smartschool.adapters.DayAdapter.Type;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.ArrayList;

public class GroupDayFragment extends BaseRecyclerFragment {

    private static final String DAY_OF_WEEK_KEY = "weekDay";
    private static final String GROUP_ID_KEY = "groupId";

    private long groupId;
    private int weekDay;

    private ArrayList<Lesson> lessons;

    public static GroupDayFragment newInstance(long groupId, int dayOfWeek) {
        GroupDayFragment fragment = new GroupDayFragment();

        Bundle args = new Bundle();
        args.putInt(DAY_OF_WEEK_KEY, dayOfWeek);
        args.putLong(GROUP_ID_KEY, groupId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weekDay = getArguments().getInt(DAY_OF_WEEK_KEY);
            groupId = getArguments().getLong(GROUP_ID_KEY);
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
                initRecyclerView(new DayAdapter(lessons, Type.GROUP));
                showRecyclerView();
            }
        }, 1000);
    }
}
