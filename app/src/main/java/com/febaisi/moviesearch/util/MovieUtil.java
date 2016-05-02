package com.febaisi.moviesearch.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;

import com.febaisi.moviesearch.controller.MovieController;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.uicontent.MovieDetailsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by felipe.baisi on 4/28/2016.
 */
public class MovieUtil {

    public static Movie parseJsonMovie(JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();
        movie.setTitle(jsonObject.getString(Movie.TITLE));
        movie.setYear(jsonObject.getString(Movie.YEAR));
        movie.setImdbID(jsonObject.getString(Movie.IMDB_ID));
        movie.setType(jsonObject.getString(Movie.TYPE));
        movie.setPoster(jsonObject.getString(Movie.POSTER));
        return movie;
    }

    public static Cursor createSuggestionCursor(List<Movie> moviesList) {
        MatrixCursor matrixCursor = new MatrixCursor(MovieController.COLUMS);
        int i = 0;
        for(Movie currentMovie : moviesList) {
            matrixCursor.addRow(new String[]{Integer.toString(i), currentMovie.getTitle(), currentMovie.getImdbID(), currentMovie.getYear(), currentMovie.getPoster()});
            i++;
        }
        return matrixCursor;
    }

    public static Movie parseJsonMovieInfo(JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();
        movie.setTitle(jsonObject.getString(Movie.TITLE));
        movie.setYear(jsonObject.getString(Movie.YEAR));
        movie.setRated(jsonObject.getString(Movie.RATED));
        movie.setReleased(jsonObject.getString(Movie.RELEASED));
        movie.setRuntime(jsonObject.getString(Movie.RUNTIME));
        movie.setGenre(jsonObject.getString(Movie.GENRE));
        movie.setDirector(jsonObject.getString(Movie.DIRECTOR));
        movie.setWriter(jsonObject.getString(Movie.WRITER));
        movie.setActors(jsonObject.getString(Movie.ACTORS));
        movie.setPlot(jsonObject.getString(Movie.PLOT));
        movie.setLanguage(jsonObject.getString(Movie.LANGUAGE));
        movie.setCountry(jsonObject.getString(Movie.COUNTRY));
        movie.setAwards(jsonObject.getString(Movie.AWARDS));
        movie.setPoster(jsonObject.getString(Movie.POSTER));
        movie.setMetascore(jsonObject.getString(Movie.METASCORE));
        movie.setImdbRating(jsonObject.getString(Movie.IMDB_RATING));
        movie.setImdbVotes(jsonObject.getString(Movie.IMDB_VOTES));
        movie.setImdbID(jsonObject.getString(Movie.IMDB_ID));
        movie.setType(jsonObject.getString(Movie.TYPE));
        movie.setResponse(jsonObject.getString(Movie.RESPONSE));
        return movie;
    }

    public static Intent createMovieInfoIntent(Context context, Movie movie, boolean shouldRequest) {
        Intent intent = new Intent(context.getApplicationContext(), MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Movie.MOVIE, movie);
        intent.putExtras(bundle);
        if (shouldRequest) {
            intent.putExtra(MovieController.REQUEST_MOVIE_INFO, shouldRequest);
        }
        return intent;
    }

    public static Movie createMovieInfoFromCursor(Cursor cursor) {
        Movie movie = new Movie();
        movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieController.COLUMS[1])));
        movie.setImdbID(cursor.getString(cursor.getColumnIndex(MovieController.COLUMS[2])));
        movie.setYear(cursor.getString(cursor.getColumnIndex(MovieController.COLUMS[3])));
        movie.setPoster(cursor.getString(cursor.getColumnIndex(MovieController.COLUMS[4])));
        return movie;
    }


}
