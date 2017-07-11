package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.valentun.smartschool.DTO.Lesson;
import com.valentun.smartschool.adapters.DayAdapter;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.ArrayList;

public class DayFragment extends BaseRecyclerFragment {

    private static final String DAY_OF_WEEK_KEY = "weekDay";
    private static final String GROUP_ID_KEY = "groupId";
    private static final String TYPE_KEY = "type";

    private long groupId;
    private int weekDay;
    private int type;

    /*
    setUserVisibleHint() is called before onViewCreated(), so, sometimes
    makeDelayedRequest() finishes too early, when onViewCreated() hasn't yet been called, so
    initRecyclerView() tries to set up NULL recyclerView instance variable and app crashes
    We need to control that

    TODO check necessity of that after retrofit's integration
    */
    private boolean isRequestNeeded = true;

    private ArrayList<Lesson> lessons;

    public static DayFragment newInstance(long groupId, int dayOfWeek, int type) {
        DayFragment fragment = new DayFragment();

        Bundle args = new Bundle();
        args.putInt(DAY_OF_WEEK_KEY, dayOfWeek);
        args.putLong(GROUP_ID_KEY, groupId);
        args.putInt(TYPE_KEY, type);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            weekDay = args.getInt(DAY_OF_WEEK_KEY);
            groupId = args.getLong(GROUP_ID_KEY);
            type = args.getInt(TYPE_KEY);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && lessons == null) {
            makeDelayedRequest();
        }
        if (!isVisibleToUser) isRequestNeeded = false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isRequestNeeded) {
            makeRequest();
        }
    }

    private void makeDelayedRequest() {
        isRequestNeeded = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                makeRequest();
            }
        }, 1000);
    }

    // TODO replace with retrofit's request
    private void makeRequest() {
        if (recyclerView != null) {
            lessons = FakeDataUtils.generateLessons();
            initRecyclerView(new DayAdapter(lessons, type));
            showRecyclerView();
        } else {
            isRequestNeeded = true;
        }
    }
}
