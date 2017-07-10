package com.valentun.smartschool.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.valentun.smartschool.Constants;
import com.valentun.smartschool.R;

import butterknife.BindView;

/**
 * Created by Valentun on 07.07.2017.
 */

public abstract class BaseWeekFragment extends Fragment {
    @BindView(R.id.viewpager) protected ViewPager pager;

    protected TabLayout tabLayout;
    protected long objectId = Constants.DEFAULT_ID_VALUE;
    protected Activity activity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        tabLayout = (TabLayout) activity.findViewById(R.id.tabLayout);

        setTitle();
    }

    protected abstract void setTitle();

    @Override
    public void onDestroy() {
        super.onDestroy();

        tabLayout.setVisibility(View.GONE);
    }


}
