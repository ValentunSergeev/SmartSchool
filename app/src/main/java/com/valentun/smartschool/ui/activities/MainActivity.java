package com.valentun.smartschool.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.valentun.smartschool.R;
import com.valentun.smartschool.ui.fragments.GroupsListFragment;
import com.valentun.smartschool.ui.fragments.MyScheduleFragment;
import com.valentun.smartschool.ui.fragments.TeachersListFragment;
import com.valentun.smartschool.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (!PreferenceUtils.isSchoolSelected(this)) startNewTaskIntent(ChooseSchoolActivity.class);

        initializeNavDrawer();

        fragmentManager = getSupportFragmentManager();

        setInitialState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class<? extends Fragment> fragmentClass = null;
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.nav_my_schedule:
                fragmentClass = MyScheduleFragment.class;
                break;
            case R.id.nav_groups:
                fragmentClass = GroupsListFragment.class;
                break;
            case R.id.nav_teachers:
                fragmentClass = TeachersListFragment.class;
                break;
            case R.id.nav_change_school:
                PreferenceUtils.deleteSelectedSchool(this);
                PreferenceUtils.deleteSelectedGroup(this);
                startNewTaskIntent(ChooseSchoolActivity.class);
                break;
            default:
                fragmentClass = MyScheduleFragment.class;
        }

        if (fragmentClass != null) replaceFragmentFromClass(fragmentClass);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void setDefaultHomeState() {
        toggle.setDrawerIndicatorEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }
    public void enableUpArrow() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.popBackStack();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initializeNavDrawer() {
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setFitsSystemWindows(true);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setInitialState() {
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, MyScheduleFragment.newInstance())
                .commit();
        navigationView.setCheckedItem(R.id.nav_my_schedule);
    }

    private void replaceFragmentFromClass(Class<? extends Fragment> fragmentClass){
        try {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragmentClass.newInstance())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
