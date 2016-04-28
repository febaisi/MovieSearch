package com.febaisi.moviesearch.controller;

import android.content.Context;

import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.model.MovieInfo;
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
    public interface ResultMovieInfoSearchListener {
        void onMovieInfoResult(MovieInfo movieInfo);
    }
    public void setResultMovieInfoSearchListener(ResultMovieInfoSearchListener resultMovieInfoSearchListener) {
        this.mResultMovieInfoSearchListener = resultMovieInfoSearchListener;
    }

    public void retrieveMovieInfo(String imdbId) {
        retrieveUrl("http://www.omdbapi.com/?i=" + imdbId, false);
    }

    @Override
    public void onResponse(Object objResponse) {
        try {
            JSONObject jsonObject = (JSONObject) objResponse;
            MovieInfo movieInfo = MovieUtil.parseJsonMovieInfo(jsonObject);
            if (mResultMovieInfoSearchListener != null) {
                mResultMovieInfoSearchListener.onMovieInfoResult(movieInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
