package com.febaisi.moviesearch.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.controller.MovieController;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.model.MovieInfo;
import com.febaisi.moviesearch.uicontent.MovieInfoActivity;
import com.squareup.picasso.Picasso;

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
            matrixCursor.addRow(new String[]{Integer.toString(i), currentMovie.getTitle(), currentMovie.getImdbID(), currentMovie.getYear(), currentMovie.getPoster()});
            i++;
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

    public static Intent createMovieInfoIntent(Context context, MovieInfo movieInfo, boolean shouldRequest) {
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
        if (shouldRequest) {
            intent.putExtra(MovieController.REQUEST_MOVIE_INFO, shouldRequest);
        }
        return intent;
    }

    public static MovieInfo createMovieInfoFromCursor(Cursor cursor) {
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setTitle(cursor.getString(cursor.getColumnIndex(MovieController.COLUMS[1])));
        movieInfo.setImdbID(cursor.getString(cursor.getColumnIndex(MovieController.COLUMS[2])));
        movieInfo.setYear(cursor.getString(cursor.getColumnIndex(MovieController.COLUMS[3])));
        movieInfo.setPoster(cursor.getString(cursor.getColumnIndex(MovieController.COLUMS[4])));
        return movieInfo;
    }

    public static MovieInfo createMovieInfoFromIntent(Intent intent) {

        MovieInfo movieInfo = new MovieInfo();
        if ((intent.hasExtra(MovieInfo.TITLE)) && (intent.getStringExtra(MovieInfo.TITLE) != null)) {
            movieInfo.setTitle(intent.getStringExtra(MovieInfo.TITLE));
        }
        if ((intent.hasExtra(MovieInfo.IMDB_ID)) && (intent.getStringExtra(MovieInfo.IMDB_ID) != null)) {
            movieInfo.setImdbID(intent.getStringExtra(MovieInfo.IMDB_ID));
        }
        if ((intent.hasExtra(MovieInfo.YEAR)) && (intent.getStringExtra(MovieInfo.YEAR) != null)) {
            movieInfo.setYear(intent.getStringExtra(MovieInfo.YEAR));
        }
        if ((intent.hasExtra(MovieInfo.PLOT)) && (intent.getStringExtra(MovieInfo.PLOT) != null)) {
            movieInfo.setPlot(intent.getStringExtra(MovieInfo.PLOT));
        }
        if ((intent.hasExtra(MovieInfo.POSTER)) && (intent.getStringExtra(MovieInfo.POSTER) != null)) {
            movieInfo.setPoster(intent.getStringExtra(MovieInfo.POSTER));
        }
        if ((intent.hasExtra(MovieInfo.ACTORS)) && (intent.getStringExtra(MovieInfo.ACTORS) != null) && !(intent.getStringExtra(MovieInfo.ACTORS).equals("N/A"))) {
            movieInfo.setActors(intent.getStringExtra(MovieInfo.ACTORS));
        }
        if ((intent.hasExtra(MovieInfo.DIRECTOR)) && (intent.getStringExtra(MovieInfo.DIRECTOR) != null) && !(intent.getStringExtra(MovieInfo.DIRECTOR).equals("N/A"))) {
            movieInfo.setDirector(intent.getStringExtra(MovieInfo.DIRECTOR));
        }
        if ((intent.hasExtra(MovieInfo.WRITER)) && (intent.getStringExtra(MovieInfo.WRITER) != null) && !(intent.getStringExtra(MovieInfo.WRITER).equals("N/A"))) {
            movieInfo.setWriter(intent.getStringExtra(MovieInfo.WRITER));
        }
        if ((intent.hasExtra(MovieInfo.RELEASED)) && (intent.getStringExtra(MovieInfo.RELEASED) != null) && !(intent.getStringExtra(MovieInfo.RELEASED).equals("N/A"))) {
            movieInfo.setReleased(intent.getStringExtra(MovieInfo.RELEASED));
        }if ((intent.hasExtra(MovieInfo.RUNTIME)) && (intent.getStringExtra(MovieInfo.RUNTIME) != null) && !(intent.getStringExtra(MovieInfo.RUNTIME).equals("N/A"))) {
            movieInfo.setRuntime(intent.getStringExtra(MovieInfo.RUNTIME));
        }
        if ((intent.hasExtra(MovieInfo.GENRE)) && (intent.getStringExtra(MovieInfo.GENRE) != null) && !(intent.getStringExtra(MovieInfo.GENRE).equals("N/A"))) {
            movieInfo.setGenre(intent.getStringExtra(MovieInfo.GENRE));
        }if ((intent.hasExtra(MovieInfo.METASCORE)) && (intent.getStringExtra(MovieInfo.METASCORE) != null) && !(intent.getStringExtra(MovieInfo.METASCORE).equals("N/A"))) {
            movieInfo.setMetascore(intent.getStringExtra(MovieInfo.METASCORE));
        }if ((intent.hasExtra(MovieInfo.AWARDS)) && (intent.getStringExtra(MovieInfo.AWARDS) != null) && !(intent.getStringExtra(MovieInfo.AWARDS).equals("N/A"))) {
            movieInfo.setAwards(intent.getStringExtra(MovieInfo.AWARDS));
        }
        if ((intent.hasExtra(MovieInfo.COUNTRY)) && (intent.getStringExtra(MovieInfo.COUNTRY) != null) && !(intent.getStringExtra(MovieInfo.COUNTRY).equals("N/A"))) {
            movieInfo.setCountry(intent.getStringExtra(MovieInfo.COUNTRY));
        }
        return movieInfo;
    }


}
