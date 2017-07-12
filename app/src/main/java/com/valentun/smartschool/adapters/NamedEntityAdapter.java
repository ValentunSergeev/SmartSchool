package com.valentun.smartschool.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valentun.smartschool.R;

import java.util.ArrayList;

public abstract class NamedEntityAdapter<T> extends RecyclerView.Adapter<NamedEntityAdapter.EntityViewHolder> {
    protected ArrayList<T> data;
    protected FragmentManager fragmentManager;

    public NamedEntityAdapter(FragmentManager fragmentManager, ArrayList<T> data) {
        this.data = data;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public EntityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.named_entity_item, parent, false);
        return new EntityViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class EntityViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;

        EntityViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.entity_name);
        }
    }
}
