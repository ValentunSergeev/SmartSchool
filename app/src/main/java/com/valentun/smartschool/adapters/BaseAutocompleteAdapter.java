package com.valentun.smartschool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentun on 26.06.2017.
 */

abstract class BaseAutocompleteAdapter<T> extends BaseAdapter implements Filterable {
    static final int MAX_RESULTS = 10;

    final Context mContext;
    private List<T> mResults;

    BaseAutocompleteAdapter(Context mContext) {
        this.mContext = mContext;
        mResults = new ArrayList<>();
    }

    @Override
    public abstract View getView(int i, View view, ViewGroup viewGroup);

    protected abstract List<T> findResults(String searchString);

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public T getItem(int i) {
        return mResults.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<T> results = findResults(constraint.toString());
                    filterResults.values = results;
                    filterResults.count = results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    mResults = (List<T>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }};
    }

    protected View inflateIfNull(View convertView, ViewGroup parentView, int layoutId) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            return inflater.inflate(layoutId, parentView, false);
        } else {
            return convertView;
        }
    }
}
