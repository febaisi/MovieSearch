package com.febaisi.moviesearch.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.febaisi.moviesearch.controller.MovieController;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.model.MovieInfo;
import com.febaisi.moviesearch.uicontent.MovieInfoActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
            matrixCursor.addRow(new String[]{Integer.toString(i), currentMovie.getTitle()});
        }

        return matrixCursor;
    }

    public static MovieInfo parseJsonMovieInfo(JSONObject jsonObject) throws JSONException {
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setTitle(jsonObject.getString(MovieInfo.TITLE));
        movieInfo.setYear(jsonObject.getString(MovieInfo.YEAR));
        movieInfo.setRated(jsonObject.getString(MovieInfo.RATED));
        movieInfo.setReleased(jsonObject.getString(MovieInfo.RELEASED));
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

    public static List<MovieInfo> createMovieInfoList(List<Movie> moviesList) {
        List<MovieInfo> moviesInfoList = new ArrayList<>();
        for (Movie movie : moviesList) {
            MovieInfo movieInfo = new MovieInfo();
            movieInfo.setTitle(movie.getTitle());
            movieInfo.setType(movie.getType());
            movieInfo.setImdbID(movie.getImdbID());
            movieInfo.setPoster(movie.getPoster());
            movieInfo.setYear(movie.getYear());
            moviesInfoList.add(movieInfo);
        }
        return moviesInfoList;
    }

    public static Intent getFilledMovieIntent(Context context, MovieInfo movieInfo) {
        Intent intent = new Intent(context.getApplicationContext(), MovieInfoActivity.class);
        intent.putExtra(MovieInfo.TITLE, movieInfo.getTitle());
        intent.putExtra(MovieInfo.YEAR, movieInfo.getYear());
        intent.putExtra(MovieInfo.RATED, movieInfo.getRated());
        intent.putExtra(MovieInfo.RELEASED, movieInfo.getReleased());
        intent.putExtra(MovieInfo.RUNTIME, movieInfo.getRuntime());
        intent.putExtra(MovieInfo.GENRE, movieInfo.getGenre());
        intent.putExtra(MovieInfo.DIRECTOR, movieInfo.getDirector());
        intent.putExtra(MovieInfo.WRITER, movieInfo.getWriter());
        intent.putExtra(MovieInfo.ACTORS, movieInfo.getActors());
        intent.putExtra(MovieInfo.PLOT, movieInfo.getPlot());
        intent.putExtra(MovieInfo.LANGUAGE, movieInfo.getLanguage());
        intent.putExtra(MovieInfo.COUNTRY, movieInfo.getCountry());
        intent.putExtra(MovieInfo.AWARDS, movieInfo.getAwards());
        intent.putExtra(MovieInfo.POSTER, movieInfo.getPoster());
        intent.putExtra(MovieInfo.METASCORE, movieInfo.getMetascore());
        intent.putExtra(MovieInfo.IMDB_RATING, movieInfo.getImdbRating());
        intent.putExtra(MovieInfo.IMDB_VOTES, movieInfo.getImdbVotes());
        intent.putExtra(MovieInfo.IMDB_ID, movieInfo.getImdbID());
        intent.putExtra(MovieInfo.TYPE, movieInfo.getType());
        intent.putExtra(MovieInfo.RESPONSE, movieInfo.getResponse());
        return intent;
    }


}
