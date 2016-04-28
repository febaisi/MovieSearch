package com.febaisi.moviesearch.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.model.MovieInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by felipebaisi on 4/28/16.
 */
public class MovieInfoController extends MovieController {


    public MovieInfoController(Context context) {
        super(context);
    }

    public void retrieveMovieInfo(String imdbId) {
        retrieveUrl("http://www.omdbapi.com/?i=" + imdbId);
    }


    @Override
    public void onResponse(Object objResponse) {
        try {
            JSONObject jsonObject = (JSONObject) objResponse;
            MovieInfo movieInfo = parseJsonMovieInfo(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private MovieInfo parseJsonMovieInfo(JSONObject jsonObject) throws JSONException {
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setTitle(jsonObject.getString(Movie.TITLE));



        return movieInfo;
    }

}
