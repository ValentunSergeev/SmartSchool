package com.valentun.smartschool.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.valentun.smartschool.DTO.Teacher;
import com.valentun.smartschool.utils.FakeDataUtils;

import butterknife.ButterKnife;

import static com.valentun.smartschool.Constants.TEACHER_TYPE;

/**
 * Created by Valentun on 10.07.2017.
 */

public class TeacherDetailFragment extends BaseWeekFragment<Teacher> {


    public static TeacherDetailFragment newInstance(long id) {
        TeacherDetailFragment fragment = new TeacherDetailFragment();
        fragment.setArguments(getArgs(id));
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        initialize();
    }

    @Override
    protected void setObjectFromExtras() {
        object = FakeDataUtils.findTeacherById(getExtraId());
    }

    protected void initialize() {
        super.initialize(object.getName(), object.getId(), TEACHER_TYPE);
    }
}
