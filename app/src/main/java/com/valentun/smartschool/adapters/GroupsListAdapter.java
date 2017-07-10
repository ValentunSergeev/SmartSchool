package com.valentun.smartschool.adapters;

import android.support.v4.app.FragmentManager;
import android.view.View;

import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.R;
import com.valentun.smartschool.ui.fragments.GroupDetailFragment;

import java.util.ArrayList;

/**
 * Created by Valentun on 08.07.2017.
 */

public class GroupsListAdapter extends NamedEntityAdapter<Group> {

    public GroupsListAdapter(ArrayList<Group> data, FragmentManager fragmentManager) {
        super(fragmentManager, data);
    }

    @Override
    public void onBindViewHolder(final NamedEntityAdapter.EntityViewHolder holder, int position) {
        final Group item = data.get(position);
        holder.name.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupDetailFragment fragment = GroupDetailFragment.newInstance(item.getId());
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
