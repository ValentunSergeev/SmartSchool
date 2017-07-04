package com.valentun.smartschool.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valentun.smartschool.DTO.NamedEntity;
import com.valentun.smartschool.R;

import java.util.ArrayList;

/**
 * Created by Valentun on 04.07.2017.
 */

public class NamedEntityAdapter extends RecyclerView.Adapter<NamedEntityAdapter.EntityViewHolder> {
    private ArrayList<NamedEntity> data;

    public NamedEntityAdapter(ArrayList<NamedEntity> data) {
        this.data = data;
    }

    @Override
    public EntityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.named_entity_item, parent, false);
        return new EntityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntityViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class EntityViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        EntityViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.entity_name);
        }

        private void bind(int position) {
            NamedEntity object = data.get(position);
            name.setText(object.getName());
        }

    }
}
