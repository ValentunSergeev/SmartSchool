package com.valentun.smartschool.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.utils.FakeDataUtils;

import butterknife.ButterKnife;

import static com.valentun.smartschool.Constants.GROUP_TYPE;

public class GroupDetailFragment extends BaseWeekFragment<Group> {

    public static GroupDetailFragment newInstance(long id) {
        GroupDetailFragment fragment = new GroupDetailFragment();
        fragment.setArguments(getArgs(id));
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        if(object != null) initialize();
    }

    @Override
    protected void setObjectFromExtras() {
        object = FakeDataUtils.findGroupById(getExtraId());
    }

    protected void initialize() {
        super.initialize(object.getName(), object.getId(), GROUP_TYPE);
    }
}
