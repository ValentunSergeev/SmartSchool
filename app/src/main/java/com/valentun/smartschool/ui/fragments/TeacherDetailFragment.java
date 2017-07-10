package com.valentun.smartschool.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valentun.smartschool.DTO.Teacher;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.TeacherSlideAdapter;
import com.valentun.smartschool.ui.activities.MainActivity;
import com.valentun.smartschool.utils.DateUtils;
import com.valentun.smartschool.utils.FakeDataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Valentun on 10.07.2017.
 */

public class TeacherDetailFragment extends Fragment {
    public static final String EXTRA_ID_KEY = "groupId";

    @BindView(R.id.viewpager)
    ViewPager pager;

    private TabLayout tabLayout;
    private MainActivity activity;
    private Teacher teacher;

    public static TeacherDetailFragment newInstance(long id) {
        TeacherDetailFragment fragment = new TeacherDetailFragment();

        Bundle args = new Bundle();
        args.putLong(EXTRA_ID_KEY, id);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();
        tabLayout = (TabLayout) activity.findViewById(R.id.tabLayout);

        setTeacherFromExtras();
    }

    @Override
    public void onStart() {
        super.onStart();

        activity.enableUpArrow();

        tabLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        initialize();
    }

    private void initialize() {
        activity.setTitle(teacher.getName());

        TeacherSlideAdapter adapter = new TeacherSlideAdapter(getChildFragmentManager(), teacher.getId());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(7);
        tabLayout.setupWithViewPager(pager);

        pager.setCurrentItem(DateUtils.ViewPagerUtils.getCurrentDayPosition());
    }

    private void setTeacherFromExtras() {
        long id = getArguments().getLong(EXTRA_ID_KEY);
        teacher = FakeDataUtils.findTeacherById(id);
    }

    @Override
    public void onStop() {
        super.onStop();

        activity.setDefaultHomeState();
        tabLayout.setVisibility(View.GONE);
    }
}
