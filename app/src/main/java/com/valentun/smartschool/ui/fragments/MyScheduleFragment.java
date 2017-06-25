package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.MyScheduleSlideAdapter;
import com.valentun.smartschool.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyScheduleFragment extends Fragment {

    @BindView(R.id.viewpager) ViewPager pager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;

    public static MyScheduleFragment newInstance() {
        MyScheduleFragment fragment = new MyScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        MyScheduleSlideAdapter adapter = new MyScheduleSlideAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        pager.setCurrentItem(DateUtils.ViewPagerUtils.getCurrentDayPosition());
    }
}
