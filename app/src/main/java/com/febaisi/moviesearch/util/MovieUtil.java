package com.febaisi.moviesearch.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;

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
        intent.putExtra(Movie.TITLE, movie.getTitle());
        intent.putExtra(Movie.YEAR, movie.getYear());
        intent.putExtra(Movie.RATED, movie.getRated());
        intent.putExtra(Movie.RELEASED, movie.getReleased());
        intent.putExtra(Movie.RUNTIME, movie.getRuntime());
        intent.putExtra(Movie.GENRE, movie.getGenre());
        intent.putExtra(Movie.DIRECTOR, movie.getDirector());
        intent.putExtra(Movie.WRITER, movie.getWriter());
        intent.putExtra(Movie.ACTORS, movie.getActors());
        intent.putExtra(Movie.PLOT, movie.getPlot());
        intent.putExtra(Movie.LANGUAGE, movie.getCountry());
        intent.putExtra(Movie.AWARDS, movie.getAwards());
        intent.putExtra(Movie.POSTER, movie.getPoster());
        intent.putExtra(Movie.METASCORE, movie.getMetascore());
        intent.putExtra(Movie.IMDB_RATING, movie.getImdbRating());
        intent.putExtra(Movie.IMDB_VOTES, movie.getImdbVotes());
        intent.putExtra(Movie.IMDB_ID, movie.getImdbID());
        intent.putExtra(Movie.TYPE, movie.getType());
        intent.putExtra(Movie.RESPONSE, movie.getResponse());
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

    public static Movie createMovieFromIntent(Intent intent) {

        Movie movie = new Movie();
        if ((intent.hasExtra(Movie.TITLE)) && (intent.getStringExtra(Movie.TITLE) != null)) {
            movie.setTitle(intent.getStringExtra(Movie.TITLE));
        }
        if ((intent.hasExtra(Movie.IMDB_ID)) && (intent.getStringExtra(Movie.IMDB_ID) != null)) {
            movie.setImdbID(intent.getStringExtra(Movie.IMDB_ID));
        }
        if ((intent.hasExtra(Movie.YEAR)) && (intent.getStringExtra(Movie.YEAR) != null)) {
            movie.setYear(intent.getStringExtra(Movie.YEAR));
        }
        if ((intent.hasExtra(Movie.PLOT)) && (intent.getStringExtra(Movie.PLOT) != null)) {
            movie.setPlot(intent.getStringExtra(Movie.PLOT));
        }
        if ((intent.hasExtra(Movie.POSTER)) && (intent.getStringExtra(Movie.POSTER) != null)) {
            movie.setPoster(intent.getStringExtra(Movie.POSTER));
        }
        if ((intent.hasExtra(Movie.ACTORS)) && (intent.getStringExtra(Movie.ACTORS) != null) && !(intent.getStringExtra(Movie.ACTORS).equals("N/A"))) {
            movie.setActors(intent.getStringExtra(Movie.ACTORS));
        }
        if ((intent.hasExtra(Movie.DIRECTOR)) && (intent.getStringExtra(Movie.DIRECTOR) != null) && !(intent.getStringExtra(Movie.DIRECTOR).equals("N/A"))) {
            movie.setDirector(intent.getStringExtra(Movie.DIRECTOR));
        }
        if ((intent.hasExtra(Movie.WRITER)) && (intent.getStringExtra(Movie.WRITER) != null) && !(intent.getStringExtra(Movie.WRITER).equals("N/A"))) {
            movie.setWriter(intent.getStringExtra(Movie.WRITER));
        }
        if ((intent.hasExtra(Movie.RELEASED)) && (intent.getStringExtra(Movie.RELEASED) != null) && !(intent.getStringExtra(Movie.RELEASED).equals("N/A"))) {
            movie.setReleased(intent.getStringExtra(Movie.RELEASED));
        }if ((intent.hasExtra(Movie.RUNTIME)) && (intent.getStringExtra(Movie.RUNTIME) != null) && !(intent.getStringExtra(Movie.RUNTIME).equals("N/A"))) {
            movie.setRuntime(intent.getStringExtra(Movie.RUNTIME));
        }
        if ((intent.hasExtra(Movie.GENRE)) && (intent.getStringExtra(Movie.GENRE) != null) && !(intent.getStringExtra(Movie.GENRE).equals("N/A"))) {
            movie.setGenre(intent.getStringExtra(Movie.GENRE));
        }if ((intent.hasExtra(Movie.METASCORE)) && (intent.getStringExtra(Movie.METASCORE) != null) && !(intent.getStringExtra(Movie.METASCORE).equals("N/A"))) {
            movie.setMetascore(intent.getStringExtra(Movie.METASCORE));
        }if ((intent.hasExtra(Movie.AWARDS)) && (intent.getStringExtra(Movie.AWARDS) != null) && !(intent.getStringExtra(Movie.AWARDS).equals("N/A"))) {
            movie.setAwards(intent.getStringExtra(Movie.AWARDS));
        }
        if ((intent.hasExtra(Movie.COUNTRY)) && (intent.getStringExtra(Movie.COUNTRY) != null) && !(intent.getStringExtra(Movie.COUNTRY).equals("N/A"))) {
            movie.setCountry(intent.getStringExtra(Movie.COUNTRY));
        }
        return movie;
    }


}
