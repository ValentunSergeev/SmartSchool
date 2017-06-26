package com.valentun.smartschool.ui.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.valentun.smartschool.DTO.School;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.SchoolAutocompleteAdapter;
import com.valentun.smartschool.ui.views.DelayAutoCompleteTextView;
import com.valentun.smartschool.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseSchoolActivity extends BaseActivity {

    @BindView(R.id.autocomplete_chooser_view) DelayAutoCompleteTextView schoolNameChooser;
    @BindView(R.id.autocomplete_progress_bar) ProgressBar progressBar;
    @BindView(R.id.school_choose_container) ConstraintLayout container;

    private School mSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_school);

        ButterKnife.bind(this);

        initializeAutoCompleteView();
    }

    @OnClick(R.id.submit_school_choose)
    public void onSubmitButtonClicked(View view) {
        if (mSchool == null) {
            Snackbar.make(container, getString(R.string.empty_school_message), Snackbar.LENGTH_LONG)
                    .show();
        } else {
            PreferenceUtils.setSelectedSchool(this, mSchool.getId());
            startNewTaskIntent(MainActivity.class);
        }
    }

    private void initializeAutoCompleteView() {
        schoolNameChooser.setThreshold(1);
        schoolNameChooser.setAdapter(new SchoolAutocompleteAdapter(this));
        schoolNameChooser.setLoadingIndicator(progressBar);
        schoolNameChooser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                School selectedSchool = (School) adapterView.getItemAtPosition(position);
                schoolNameChooser.setText(selectedSchool.getName());
                mSchool = selectedSchool;
            }
        });
    }
}
