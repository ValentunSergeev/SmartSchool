package com.valentun.smartschool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.valentun.smartschool.DTO.School;
import com.valentun.smartschool.R;
import com.valentun.smartschool.utils.MockDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentun on 22.06.2017.
 */

public class ChooseSchoolAutocompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;

    private final Context mContext;
    private List<School> mResults;

    public ChooseSchoolAutocompleteAdapter(Context context) {
        mContext = context;
        mResults = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public School getItem(int index) {
        return mResults.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.choose_school_autocomplete_item, parent, false);
        }

        School school = getItem(position);
        ((TextView) convertView.findViewById(R.id.autocomplete_item_city)).setText(school.getCity());
        ((TextView) convertView.findViewById(R.id.autocomplete_item_name)).setText(school.getName());

        return convertView;
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<School> schools = findSchools(constraint.toString());
                    filterResults.values = schools;
                    filterResults.count = schools.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    mResults = (List<School>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }};
    }

    /**
     * Returns a search result for the given school title.
     */
    private List<School> findSchools(String schoolName) {
        //TODO add retrofit request instead mock data search
        return MockDataUtils.findSchools(schoolName, MAX_RESULTS);
    }

}