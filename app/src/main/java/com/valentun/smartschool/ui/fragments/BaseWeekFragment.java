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
import com.valentun.smartschool.adapters.SlideAdapter;
import com.valentun.smartschool.ui.activities.MainActivity;
import com.valentun.smartschool.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Valentun on 07.07.2017.
 */

public abstract class BaseWeekFragment<T> extends Fragment {
    public static final String EXTRA_ID_KEY = "groupId";

    protected TabLayout tabLayout;
    protected MainActivity activity;
    protected T object;

    @BindView(R.id.viewpager) ViewPager pager;

    protected abstract void setObjectFromExtras();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();
        tabLayout = (TabLayout) activity.findViewById(R.id.tabLayout);

        setObjectFromExtras();
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
    }

    @Override
    public void onStart() {
        super.onStart();

        activity.enableUpArrow();
        tabLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();

        activity.setDefaultHomeState();
        tabLayout.setVisibility(View.GONE);
    }

    protected static Bundle getArgs(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_ID_KEY, id);
        return bundle;
    }

    protected long getExtraId() {
        return getArguments().getLong(EXTRA_ID_KEY);
    }

    protected void initialize(String title, long id, int type) {
        setTitle(title);

        SlideAdapter adapter = new SlideAdapter(getChildFragmentManager(), id, type);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(7);
        tabLayout.setupWithViewPager(pager);

        pager.setCurrentItem(DateUtils.ViewPagerUtils.getCurrentDayPosition());
    }

    protected void setTitle(String title) {
        if (title != null) activity.setTitle(title);
    }
}
