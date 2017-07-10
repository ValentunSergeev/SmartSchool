package com.valentun.smartschool.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.valentun.smartschool.ui.fragments.GroupDayFragment;
import com.valentun.smartschool.utils.DateUtils;

/**
 * Created by Valentun on 25.06.2017.
 */

@SuppressWarnings("WrongConstant")
public class GroupSlideAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 7;
    private long groupId;

    public GroupSlideAdapter(FragmentManager fm, long groupId) {
        super(fm);
        this.groupId = groupId;
    }

    @Override
    public Fragment getItem(int position) {
        return GroupDayFragment.newInstance(groupId, position);
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