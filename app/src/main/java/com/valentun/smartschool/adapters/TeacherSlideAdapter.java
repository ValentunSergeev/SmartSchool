package com.valentun.smartschool.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.valentun.smartschool.ui.fragments.TeacherDayFragment;
import com.valentun.smartschool.utils.DateUtils;

/**
 * Created by Valentun on 10.07.2017.
 */

public class TeacherSlideAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 7;
    private long groupId;

    public TeacherSlideAdapter(FragmentManager fm, long teacherId) {
        super(fm);
        this.groupId = teacherId;
    }

    @Override
    public Fragment getItem(int position) {
        return TeacherDayFragment.newInstance(groupId, position);
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
