package com.valentun.smartschool.adapters;

import android.support.v4.app.FragmentManager;
import android.view.View;

import com.valentun.smartschool.DTO.Teacher;
import com.valentun.smartschool.R;
import com.valentun.smartschool.ui.fragments.TeacherDetailFragment;

import java.util.ArrayList;

public class TeachersListAdapter extends NamedEntityAdapter<Teacher> {

    public TeachersListAdapter(FragmentManager fragmentManager, ArrayList<Teacher> data) {
        super(fragmentManager, data);
    }

    @Override
    public void onBindViewHolder(NamedEntityAdapter.EntityViewHolder holder, int position) {
        final Teacher item = data.get(position);
        holder.name.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeacherDetailFragment fragment = TeacherDetailFragment.newInstance(item.getId());
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
