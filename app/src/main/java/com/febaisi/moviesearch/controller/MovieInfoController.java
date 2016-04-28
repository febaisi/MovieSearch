package com.febaisi.moviesearch.controller;

import android.content.Context;

import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.model.MovieInfo;

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
        movieInfo.setTitle(jsonObject.getString(MovieInfo.TITLE));
        movieInfo.setYear(jsonObject.getString(MovieInfo.YEAR));
        movieInfo.setRated(jsonObject.getString(MovieInfo.RATED));
        movieInfo.setRuntime(jsonObject.getString(MovieInfo.RUNTIME));
        movieInfo.setGenre(jsonObject.getString(MovieInfo.GENRE));
        movieInfo.setDirector(jsonObject.getString(MovieInfo.DIRECTOR));
        movieInfo.setWriter(jsonObject.getString(MovieInfo.WRITER));
        movieInfo.setActors(jsonObject.getString(MovieInfo.ACTORS));
        movieInfo.setPlot(jsonObject.getString(MovieInfo.PLOT));
        movieInfo.setLanguage(jsonObject.getString(MovieInfo.LANGUAGE));
        movieInfo.setCountry(jsonObject.getString(MovieInfo.COUNTRY));
        movieInfo.setAwards(jsonObject.getString(MovieInfo.AWARDS));
        movieInfo.setPoster(jsonObject.getString(MovieInfo.POSTER));
        movieInfo.setMetascore(jsonObject.getString(MovieInfo.METASCORE));
        movieInfo.setImdbRating(jsonObject.getString(MovieInfo.IMDB_RATING));
        movieInfo.setImdbVotes(jsonObject.getString(MovieInfo.IMDB_VOTES));
        movieInfo.setImdbID(jsonObject.getString(MovieInfo.IMDB_ID));
        movieInfo.setType(jsonObject.getString(MovieInfo.TYPE));
        movieInfo.setResponse(jsonObject.getString(MovieInfo.RESPONSE));

        return movieInfo;
    }

}
