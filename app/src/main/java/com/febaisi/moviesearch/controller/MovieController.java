package com.febaisi.moviesearch.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.VolleyApplication;
import com.febaisi.moviesearch.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipebaisi on 4/27/16.
 */
public class MovieController implements  Response.ErrorListener, Response.Listener {

    public static String[] COLUMS = new String[]{BaseColumns._ID, "Title"};
    public static String JSON_REQUEST_TAG = "SUGGESTION_REQUEST";
    public Context mContext;

    public MovieController (Context context){
        this.mContext = context;
    }


    //Implement interface
    private Searchable mSearchable;
    public interface Searchable {
        void notifyAdapter(Cursor cursor);
    }
    public void setSearchableListener(Searchable searchable) {
        this.mSearchable = searchable;
    }

    private ResultMovieTitleSearchListener mResultMovieTitleSearchListener;
    public interface ResultMovieTitleSearchListener {
        void onMovieListResult(List<Movie> moviesList);
    }
    public void setOnMovieListResult(ResultMovieTitleSearchListener resultMovieTitleSearchListener) {
        this.mResultMovieTitleSearchListener = resultMovieTitleSearchListener;
    }


    public void retrieveTitleSearch(String titleQuery) {
        retrieveUrl("http://www.omdbapi.com/?s=" + titleQuery);
    }

    public void retrieveUrl(String url) {
        JsonObjectRequest request = new JsonObjectRequest(url, null, this, this);
        request.setTag(JSON_REQUEST_TAG);
        VolleyApplication.getInstance().getRequestQueue().cancelAll(JSON_REQUEST_TAG);
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }


    private Movie parseJsonMovie(JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();
        movie.setTitle(jsonObject.getString(Movie.TITLE));
        movie.setYear(jsonObject.getString(Movie.YEAR));
        movie.setImdbID(jsonObject.getString(Movie.IMDB_ID));
        movie.setType(jsonObject.getString(Movie.TYPE));
        movie.setPoster(jsonObject.getString(Movie.POSTER));

        return movie;
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
                moviesList.add(parseJsonMovie(searchArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            Log.e(mContext.getResources().getString(R.string.app_name), e.toString());
        }

        if (mSearchable != null) {
            mSearchable.notifyAdapter(createSuggestionCursor(moviesList));
        }
        if (mResultMovieTitleSearchListener != null) {

            mResultMovieTitleSearchListener.onMovieListResult(moviesList);
        }
    }

    private Cursor createSuggestionCursor(List<Movie> moviesList) {
        MatrixCursor matrixCursor = new MatrixCursor(COLUMS);
        int i = 0;
        for(Movie currentMovie : moviesList) {
            matrixCursor.addRow(new String[]{Integer.toString(i), currentMovie.getTitle()});
        }

        return matrixCursor;
    }

}
