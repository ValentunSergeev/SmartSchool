package com.valentun.smartschool.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.R;

import java.util.ArrayList;

public class GroupSearchAdapter extends BaseSearchAdapter<Group, GroupSearchAdapter.SearchGroupHolder>
        implements Filterable {

    public interface ItemClickCallback {
        void onItemClicked(Group item);
    }

    private ItemClickCallback callback;

    public GroupSearchAdapter(ArrayList<Group> data, ItemClickCallback callback) {
        super(data);
        this.callback = callback;
    }

    @Override
    public SearchGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.group_search_item, parent, false);
        return new SearchGroupHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchGroupHolder holder, int position) {
        Group item = searchResult.get(position);
        holder.bind(item);
    }


    protected ArrayList<Group> findSchools(String schoolName) {
        ArrayList<Group> result = new ArrayList<>();
        for (Group school : data) {
            String name = school.getName().toLowerCase();
            if (name.contains(schoolName.toLowerCase())) {
                result.add(school);
            }
        }
        return result;
    }

    class SearchGroupHolder extends RecyclerView.ViewHolder {
        private TextView nameView;

        SearchGroupHolder(View itemView) {
            super(itemView);

            nameView = (TextView) itemView.findViewById(R.id.group_search_title);
        }

        void bind(final Group item) {
            nameView.setText(item.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClicked(item);
                }
            });
        }
    }
}
