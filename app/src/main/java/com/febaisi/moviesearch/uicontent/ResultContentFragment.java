package com.febaisi.moviesearch.uicontent;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.adapter.SearchResultAdapter;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.controller.MovieController;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipe.baisi on 4/27/2016.
 */
public class ResultContentFragment extends Fragment implements MovieController.ResultSearchListener {

    public static String QUERY = "QUERY";
    private String mTitleQuery;
    private MovieController mMovieController;
    private CircularProgressView mCircularProgressView;
    private RecyclerView mRecyclerView;
    private List<Movie>  mMovieList;
    private SearchResultAdapter mSearchResultAdapter;

    @Nullable
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

        mMovieList = new ArrayList<>();
        mSearchResultAdapter = new SearchResultAdapter(mMovieList);
        mRecyclerView.setAdapter(mSearchResultAdapter);

        mMovieController = new MovieController(view.getContext(), this);
        mMovieController.retrieveTitleSearch(mTitleQuery);

    }

    @Override
    public void onMovieListResult(List<Movie> moviesList) {
        mSearchResultAdapter.updateDataSet(moviesList);
        mSearchResultAdapter.notifyDataSetChanged();
        mCircularProgressView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);

    }
}
