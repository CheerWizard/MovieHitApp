package com.example.moviehitapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviehitapp.R;
import com.example.moviehitapp.adapter.MoviesAdapter;
import com.example.moviehitapp.annotations.Ui;
import com.example.moviehitapp.business_logic.data.Movie;
import com.example.moviehitapp.constants.SortOrder;
import com.example.moviehitapp.utils.ConnectivityUtils;
import com.example.moviehitapp.utils.sorters.MoviesSorter;
import com.example.moviehitapp.utils.factories.ViewModelFactory;
import com.example.moviehitapp.viewmodels.MovieResponseViewModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

@Ui
public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    //binds recycler view with it's id
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    //adapter
    private MoviesAdapter moviesAdapter;
    //loading text view
    @BindView(R.id.loading) TextView loadingTextView;
    //binds layout to it's id
    @BindView(R.id.main_content) SwipeRefreshLayout swipeRefreshLayout;
    //injects view model factory
    @Inject ViewModelFactory viewModelFactory;
    //corresponding view model
    private MovieResponseViewModel movieResponseViewModel;
    //global var
    private String sortOrder;
    //global util helper class for checking system connection
    private ConnectivityManager connectivityManager;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //first we need to save network info
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityUtils.setNetworkInfo(connectivityManager.getActiveNetworkInfo());
        //init view models
        initViewModels();
        //init adapters
        initAdapters();
        //init views
        initViews();
        //creates movies observer
        observeMovies();
        //creates loading observer for this lifecycle owner
        observeLoading();
        //creates error observer for this lifecycle owner
        observeErrors();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //save network info again after pausing screen
        ConnectivityUtils.setNetworkInfo(connectivityManager.getActiveNetworkInfo());
        //check for shared preferences
        checkSharedPreferences();
        //set listeners
        setListeners();
    }

    private synchronized void initViews() {
        //set properties on recycler view
        if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT))
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);
        //set properties to swipe refresher
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
    }

    private synchronized void setListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private synchronized void initViewModels() {
        movieResponseViewModel = ViewModelProviders.of(this , viewModelFactory).get(MovieResponseViewModel.class);
    }

    private synchronized void initAdapters() {
        //init adapter
        moviesAdapter = new MoviesAdapter(this);
    }

    private synchronized void checkSharedPreferences() {
        //check shared preferences
        sortOrder = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getString(this.getString(R.string.pref_sort_order_key) , "");
        if (Objects.requireNonNull(sortOrder).equals(this.getString(R.string.pref_most_popular))) movieResponseViewModel.fetchMostPopularMovies();
        else movieResponseViewModel.fetchHighestRatedMovies();
    }

    private synchronized void observeMovies() {
        movieResponseViewModel.getMoviesResponseLiveData().observe(this , moviesResponse -> {
            if (moviesResponse != null) {
                List<Movie> movieList = moviesResponse.getMovies();
                if (sortOrder != null && sortOrder.equals(this.getString(R.string.pref_sort_by_original_title)))
                    movieList = MoviesSorter.sort(movieList , SortOrder.BY_MOVIES_ORIGINAL_TITLE);
                recyclerView.setVisibility(View.VISIBLE);
                moviesAdapter.updateData(movieList);
            }
        });
    }

    private synchronized void observeLoading() {
        movieResponseViewModel.getLoadingLiveData().observe(this, isLoading -> {
            if (isLoading != null) animateLoading(isLoading);
        });
    }

    private synchronized void observeErrors() {
        movieResponseViewModel.getErrorLiveData().observe(this, isError -> {
            if (isError != null) if (isError) animateErrors();
        });
    }

    private void animateErrors() {
        //emulate error animation here
    }

    private void animateLoading(boolean isLoading) {
        loadingTextView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        if (isLoading) recyclerView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(this , SettingsActivity.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        initViews();
        Toast.makeText(MainActivity.this, getString(R.string.movies_refreshed), Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}

