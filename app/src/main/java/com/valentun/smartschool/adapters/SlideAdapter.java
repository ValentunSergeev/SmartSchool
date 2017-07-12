package com.valentun.smartschool.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.valentun.smartschool.ui.fragments.DayFragment;
import com.valentun.smartschool.utils.DateUtils;

public class SlideAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 7;
    private long groupId;
    private int type;

    public SlideAdapter(FragmentManager fm, long teacherId, int type) {
        super(fm);
        this.groupId = teacherId;
        this.type = type;
    }

    @Override
    public Fragment getItem(int position) {
        return DayFragment.newInstance(groupId, position, type);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return DateUtils.ViewPagerUtils.getWeekDayNameByPosition(position);
    }
}
