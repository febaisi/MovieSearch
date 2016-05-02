package com.febaisi.moviesearch.controller;

import android.content.Context;
import android.provider.BaseColumns;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.VolleyApplication;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.util.MovieUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipebaisi on 4/27/16.
 */
public class MovieController implements  Response.ErrorListener, Response.Listener {

    public static String[] COLUMS = new String[]{BaseColumns._ID, Movie.TITLE, Movie.IMDB_ID, Movie.YEAR, Movie.POSTER};
    public static String JSON_REQUEST_TAG = "CANCELABLE_JSON_REQUEST_TAG";
    public static String REQUEST_MOVIE_INFO = "REQUEST_MOVIE_INFO";
    private Context mContext;
    private ResultMovieTitleSearchListener mResultMovieTitleSearchListener;

    public MovieController (Context context){
        this.mContext = context;
    }

    public void retrieveTitleSearch(String titleQuery) {
        retrieveUrl("http://www.omdbapi.com/?s=" + titleQuery, true);
    }

    public void retrieveUrl(String url, boolean cancelable) {
        JsonObjectRequest request = new JsonObjectRequest(url, null, this, this);
        request.setTag(JSON_REQUEST_TAG);
        if (cancelable) {
            VolleyApplication.getInstance().getRequestQueue().cancelAll(JSON_REQUEST_TAG);
        }
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(mContext.getResources().getString(R.string.app_name), error.toString());
    }

    @Override
    public void onResponse(Object objResponse) {
        List<Movie> moviesList = new ArrayList<>();
        JSONObject response =  (JSONObject) objResponse;
        try {
            JSONArray searchArray = response.getJSONArray("Search");
            for(int i = 0; i<searchArray.length(); i++){
                moviesList.add(MovieUtil.parseJsonMovie(searchArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            Log.e(mContext.getResources().getString(R.string.app_name), e.toString());
        }

        //notify listeners
        if (mResultMovieTitleSearchListener != null) {
            mResultMovieTitleSearchListener.onMovieListResult(moviesList);
        }
    }

    //settings interfaces
    public interface ResultMovieTitleSearchListener {
        void onMovieListResult(List<Movie> moviesList);
    }
    public void setOnMovieListResult(ResultMovieTitleSearchListener resultMovieTitleSearchListener) {
        this.mResultMovieTitleSearchListener = resultMovieTitleSearchListener;
    }

}
