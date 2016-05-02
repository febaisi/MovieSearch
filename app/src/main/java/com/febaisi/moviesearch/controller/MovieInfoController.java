package com.febaisi.moviesearch.controller;

import android.content.Context;

import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.util.MovieUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by felipebaisi on 4/28/16.
 */
public class MovieInfoController extends MovieController {

    public MovieInfoController(Context context) {
        super(context);
    }

    // Request movie info interface
    private ResultMovieInfoSearchListener mResultMovieInfoSearchListener;

    public void retrieveMovieInfo(String imdbId) {
        retrieveUrl("http://www.omdbapi.com/?i=" + imdbId, false);
    }

    @Override
    public void onResponse(Object objResponse) {
        try {
            JSONObject jsonObject = (JSONObject) objResponse;
            Movie movie = MovieUtil.parseJsonMovieInfo(jsonObject);

            if (mResultMovieInfoSearchListener != null) {
                mResultMovieInfoSearchListener.onMovieInfoResult(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //settings interface & listeners
    public interface ResultMovieInfoSearchListener {
        void onMovieInfoResult(Movie movie);
    }
    public void setResultMovieInfoSearchListener(ResultMovieInfoSearchListener resultMovieInfoSearchListener) {
        this.mResultMovieInfoSearchListener = resultMovieInfoSearchListener;
    }
}
