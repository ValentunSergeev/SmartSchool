package com.valentun.smartschool.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.R;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.List;

/**
 * Created by Valentun on 26.06.2017.
 */

public class GroupAutocompleteAdapter extends BaseAutocompleteAdapter<Group> {
    public GroupAutocompleteAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflateIfNull(view, parent, R.layout.group_autocomplete_item);

        Group namedEntity = getItem(position);

        ((TextView) view.findViewById(R.id.group_autocomplete_title)).setText(namedEntity.getName());

        return view;
    }

    @Override
    protected List<Group> findResults(String searchString) {
        return FakeDataUtils.findGroups(searchString, MAX_RESULTS);
    }
}
