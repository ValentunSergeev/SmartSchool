package com.valentun.smartschool.listeners;

import android.os.Handler;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;

import com.valentun.smartschool.Constants;

/**
 * Created by Valentun on 12.07.2017.
 */

public class SearchListener implements SearchView.OnQueryTextListener {
    private Handler handler = new Handler();
    private ProgressBar progressBar;
    private Filterable filterable;
    private View resultView;

    // if you do not use progress bar
    public SearchListener(Filterable filterable, View resultView) {
        this.filterable = filterable;
    }

    // if you use progress bar
    public SearchListener(Filterable filterable, View resultView, ProgressBar progressBar) {
        this.filterable = filterable;
        this.resultView = resultView;
        this.progressBar = progressBar;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String s) {
        handler.removeCallbacksAndMessages(null);

        if (s.length() > 0) {
            if (progressBar != null) progressBar.setVisibility(View.VISIBLE);

            resultView.setVisibility(View.GONE);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    filterable.getFilter().filter(s, new SearchFilterListener());
                }
            }, Constants.SEARCH_DELAY);
        } else {
            progressBar.setVisibility(View.GONE);
        }
        return true;
    }

    private class SearchFilterListener implements Filter.FilterListener {

        @Override
        public void onFilterComplete(int i) {
            if (progressBar != null) progressBar.setVisibility(View.GONE);
            resultView.setVisibility(View.VISIBLE);
        }
    }
}
