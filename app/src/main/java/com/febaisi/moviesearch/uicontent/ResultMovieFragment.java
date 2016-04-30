package com.febaisi.moviesearch.uicontent;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.adapter.SearchResultAdapter;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.controller.MovieController;
import com.febaisi.moviesearch.model.MovieInfo;
import com.febaisi.moviesearch.util.MovieUtil;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipe.baisi on 4/27/2016.
 */
public class ResultMovieFragment extends Fragment implements MovieController.ResultMovieTitleSearchListener {

    public static String QUERY = "QUERY";
    private String mTitleQuery;
    private MovieController mMovieController;
    private CircularProgressView mCircularProgressView;
    private RecyclerView mRecyclerView;
    private List<MovieInfo>  mMoviesInfoList;
    private SearchResultAdapter mSearchResultAdapter;
    private int requestPage = 1;
    private int mMaxResultSet;
    public static int PAGE_ROW = 6;

    //Handle Pagination
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mTitleQuery = bundle.getString(QUERY);
        }
        return inflater.inflate(R.layout.content_card_result, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCircularProgressView = (CircularProgressView) view.findViewById(R.id.search_progress_view);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_result);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        loading = true;
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                            loading = false;
                            handleList();
                        }
                    }
                }
            }
        });

        mMoviesInfoList = new ArrayList<>();
        mSearchResultAdapter = new SearchResultAdapter(mMoviesInfoList);
        mRecyclerView.setAdapter(mSearchResultAdapter);

        mMovieController = new MovieController(view.getContext());
        mMovieController.setOnMovieListResult(this);
        mMovieController.retrieveTitleSearch(mTitleQuery, null);
    }

    @Override
    public void onMovieListResult(List<Movie> moviesList, int resultSet) {
        MovieUtil.addMovieInfo(mMoviesInfoList, MovieUtil.createMovieInfoList(moviesList));
        mSearchResultAdapter.updateDataSet(mMoviesInfoList);
        mMaxResultSet = resultSet;
        handleList();
    }

    private void handleList() {
        //In charge to calc adapter getItemCount (Rows in list)
        if(mMaxResultSet <= (mSearchResultAdapter.getItemCount() + PAGE_ROW) ){
            mSearchResultAdapter.setCurrentRows(mMaxResultSet);
            loading = false;
            mSearchResultAdapter.notifyDataSetChanged();
            mCircularProgressView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else if ((mSearchResultAdapter.getItemCount() + PAGE_ROW) > mMoviesInfoList.size()) {
           //load more movies
            requestPage++;
            mMovieController.retrieveTitleSearch(mTitleQuery, Integer.toString(requestPage));
        } else {
            loading = true;
            mSearchResultAdapter.setCurrentRows(mSearchResultAdapter.getItemCount()+PAGE_ROW);
            mSearchResultAdapter.notifyDataSetChanged();
            mCircularProgressView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
