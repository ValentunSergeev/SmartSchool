package com.valentun.smartschool.adapters;

import android.support.v7.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * Created by Valentun on 12.07.2017.
 */

public abstract class BaseSearchAdapter<T, E extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<E>
        implements Filterable {
    protected ArrayList<T> data;
    protected ArrayList<T> searchResult;

    public BaseSearchAdapter(ArrayList<T> data) {
        this.data = data;
        searchResult = data;
    }

    @Override
    public int getItemCount() {
        return searchResult.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    ArrayList<T> results = findSchools(constraint.toString());
                    filterResults.values = results;
                    filterResults.count = results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.values != null) {
                    searchResult = (ArrayList<T>) results.values;
                } else {
                    searchResult = new ArrayList<>();
                }
                notifyDataSetChanged();
            }};
    }

    protected abstract ArrayList<T> findSchools(String constraint);
}
