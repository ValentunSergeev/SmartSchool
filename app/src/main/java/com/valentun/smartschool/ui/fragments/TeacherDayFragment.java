package com.valentun.smartschool.ui.fragments;

import android.os.Bundle;
import android.os.Handler;

import com.valentun.smartschool.DTO.Lesson;
import com.valentun.smartschool.adapters.DayAdapter;
import com.valentun.smartschool.adapters.DayAdapter.Type;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.ArrayList;

/**
 * Created by Valentun on 10.07.2017.
 */

public class TeacherDayFragment extends BaseRecyclerFragment{
    private static final String DAY_OF_WEEK_KEY = "weekDay";
    private static final String TEACHER_ID_KEY = "teacherId";

    private long groupId;
    private int weekDay;

    private ArrayList<Lesson> lessons;

    public static TeacherDayFragment newInstance(long groupId, int dayOfWeek) {
        TeacherDayFragment fragment = new TeacherDayFragment();

        Bundle args = new Bundle();
        args.putInt(DAY_OF_WEEK_KEY, dayOfWeek);
        args.putLong(TEACHER_ID_KEY, groupId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weekDay = getArguments().getInt(DAY_OF_WEEK_KEY);
            groupId = getArguments().getLong(TEACHER_ID_KEY);
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
                initRecyclerView(new DayAdapter(lessons, Type.TEACHER));
                showRecyclerView();
            }
        }, 1000);
    }
}
