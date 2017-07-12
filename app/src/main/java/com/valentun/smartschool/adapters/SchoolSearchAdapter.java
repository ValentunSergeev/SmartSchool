package com.valentun.smartschool.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valentun.smartschool.DTO.School;
import com.valentun.smartschool.R;
import com.valentun.smartschool.ui.activities.MainActivity;
import com.valentun.smartschool.utils.PreferenceUtils;

import java.util.ArrayList;

public class SchoolSearchAdapter extends BaseSearchAdapter<School, SchoolSearchAdapter.SearchSchoolHolder> {


    public SchoolSearchAdapter(ArrayList<School> data) {
        super(data);
    }

    @Override
    public SearchSchoolHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.school_search_item, parent, false);
        return new SearchSchoolHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchSchoolHolder holder, int position) {
        School item = searchResult.get(position);
        holder.bind(item);
    }


    protected ArrayList<School> findSchools(String schoolName) {
        ArrayList<School> result = new ArrayList<>();
        for (School school : data) {
            String name = school.getName().toLowerCase();
            if (name.contains(schoolName.toLowerCase())) {
                result.add(school);
            }
        }
        return result;
    }

    class SearchSchoolHolder extends RecyclerView.ViewHolder {
        private TextView cityView, nameView;

        SearchSchoolHolder(View itemView) {
            super(itemView);

            cityView = (TextView) itemView.findViewById(R.id.search_item_city);
            nameView = (TextView) itemView.findViewById(R.id.search_item_name);
        }

        protected void bind(final School item) {
            final Context context = itemView.getContext();

            cityView.setText(item.getCity());
            nameView.setText(item.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PreferenceUtils.setSelectedSchool(context, item.getId());

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

    }
}
