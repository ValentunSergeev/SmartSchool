package com.valentun.smartschool.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.valentun.smartschool.DTO.School;
import com.valentun.smartschool.R;
import com.valentun.smartschool.adapters.SchoolSearchAdapter;
import com.valentun.smartschool.listeners.SearchListener;
import com.valentun.smartschool.utils.FakeDataUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class ChooseSchoolActivity extends BaseActivity {


    @BindView(R.id.search_school_progress) ProgressBar progressBar;
    @BindView(R.id.school_choose_container) LinearLayout container;
    @BindView(R.id.choose_school_list) RecyclerView recyclerView;

    private MenuItem searchMenuItem;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_school);

        ButterKnife.bind(this);

        makeRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchMenuItem.setVisible(false);

        return true;
    }

    private void makeRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initialize(FakeDataUtils.allSchools);
            }
        }, 1000);
    }

    private void initialize(ArrayList<School> data) {
        SchoolSearchAdapter adapter = new SchoolSearchAdapter(data);
        SearchListener listener = new SearchListener(adapter, recyclerView, progressBar);

        searchView.setOnQueryTextListener(listener);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setVisibility(View.VISIBLE);
        searchMenuItem.setVisible(true);
        progressBar.setVisibility(GONE);
    }
}
