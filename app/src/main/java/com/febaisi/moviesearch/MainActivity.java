package com.febaisi.moviesearch;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.febaisi.moviesearch.controller.MovieController;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.uicontent.MovieDetailsActivity;
import com.febaisi.moviesearch.uicontent.ResultMovieTitleContentFragment;
import com.febaisi.moviesearch.uicontent.SearchContentFragment;
import com.febaisi.moviesearch.util.MovieUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener, SearchView.OnSuggestionListener,
        MovieController.ResultMovieTitleSearchListener {

    private SimpleCursorAdapter mSearchAdapter;
    private MovieController mMovieController;
    private FragmentManager mFragmentManager;
    private SearchView mSearchView;
    private Cursor mSuggestionCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Creating suggestion search adapter
        mSearchAdapter = new SimpleCursorAdapter(this, R.layout.search_suggestion_row, null, new String[]{"Title"},
                new int[]{R.id.suggestion_title}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //Create 'Searchable' class listener in MovieController
        mMovieController = new MovieController(this);
        mMovieController.setOnMovieListResult(this);

        //Present first fragment
        mFragmentManager = getFragmentManager();
        replaceTopFragment(new SearchContentFragment());
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //Set Search Interface
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnSuggestionListener(this);
        mSearchView.setSuggestionsAdapter(mSearchAdapter);


        //handle toolbar back buttons
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.action_search),
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        return true;
                    }
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                        if (mFragmentManager.getBackStackEntryCount() > 1) {
                            getFragmentManager().popBackStack();
                        }
                        return true;
                    }
                });
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString(ResultMovieTitleContentFragment.QUERY, query);
        ResultMovieTitleContentFragment resultContentFragment = new ResultMovieTitleContentFragment();
        resultContentFragment.setArguments(bundle);
        replaceTopFragment(resultContentFragment);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mMovieController.retrieveTitleSearch(newText);
        return true;
    }

    @Override
    public boolean onSuggestionSelect(int position) {
        return false;
    }

    @Override
    public boolean onSuggestionClick(int position) {
        //on user click in seach suggestions
        if (mSuggestionCursor != null && mSuggestionCursor.moveToFirst()) {
            mSuggestionCursor.moveToFirst();
            while (mSuggestionCursor.isAfterLast() == false) {
                if (mSuggestionCursor.getString(mSuggestionCursor.getColumnIndex(MovieController.COLUMS[0])).equals(Integer.toString(position))) {
                    startActivity(MovieUtil.createMovieInfoIntent(this,MovieUtil.createMovieInfoFromCursor(mSuggestionCursor), true ));
                    break;
                }
                mSuggestionCursor.moveToNext();
            }
        }
        return true;
    }

    private void replaceTopFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onMovieListResult(List<Movie> moviesList) {
        mSuggestionCursor = MovieUtil.createSuggestionCursor(moviesList);
        mSearchAdapter.changeCursor(mSuggestionCursor);
    }
}
