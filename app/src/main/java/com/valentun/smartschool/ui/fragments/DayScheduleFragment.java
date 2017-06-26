package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valentun.smartschool.R;
import com.valentun.smartschool.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayScheduleFragment extends Fragment {

    public static final String DAY_OF_WEEK_KEY = "weekDay";

    private static long groupId = PreferenceUtils.DEFAULT_ID_VALUE;

    @BindView(R.id.day_detail_text) TextView textView;

    private int weekDay;

    public static DayScheduleFragment newInstance(int dayOfWeek) {
        DayScheduleFragment fragment = new DayScheduleFragment();

        Bundle args = new Bundle();
        args.putInt(DAY_OF_WEEK_KEY, dayOfWeek);

        fragment.setArguments(args);
        return fragment;
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

        showFakeMessage();
    }

    //TODO delete when after retrofit's integration
    private void showFakeMessage(){
        String message = "Group id:" + groupId + "\n" + "Week day id: " + weekDay;
        textView.setText(message);
    }

    public static void setGroupId(long groupId) {
        DayScheduleFragment.groupId = groupId;
    }
}
