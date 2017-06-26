package com.valentun.smartschool.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valentun.smartschool.DTO.School;
import com.valentun.smartschool.R;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.List;

/**
 * Created by Valentun on 22.06.2017.
 */

public class SchoolAutocompleteAdapter extends BaseAutocompleteAdapter<School> {

    public SchoolAutocompleteAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflateIfNull(convertView, parent, R.layout.choose_school_autocomplete_item);

        School school = getItem(position);
        ((TextView) convertView.findViewById(R.id.autocomplete_item_city)).setText(school.getCity());
        ((TextView) convertView.findViewById(R.id.autocomplete_item_name)).setText(school.getName());

        return convertView;
    }

    @Override
    protected List<School> findResults(String searchString) {
        return FakeDataUtils.findSchools(searchString, MAX_RESULTS);
    }
}