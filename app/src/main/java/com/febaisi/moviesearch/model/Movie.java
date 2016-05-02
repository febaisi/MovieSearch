package com.febaisi.moviesearch.model;

/**
 * Created by felipebaisi on 4/27/16.
 */
public class Movie {

    public static String TITLE = "Title";
    public static String YEAR  = "Year";
    public static String IMDB_ID  = "imdbID";
    public static String TYPE  = "Type";
    public static String POSTER  = "Poster";
    public static String RATED = "Rated";
    public static String RELEASED = "Released";
    public static String RUNTIME = "Runtime";
    public static String GENRE = "Genre";
    public static String DIRECTOR = "Director";
    public static String WRITER = "Writer";
    public static String ACTORS = "Actors";
    public static String PLOT = "Plot";
    public static String LANGUAGE = "Language";
    public static String COUNTRY = "Country";
    public static String AWARDS = "Awards";
    public static String METASCORE = "Metascore";
    public static String IMDB_RATING = "imdbRating";
    public static String IMDB_VOTES = "imdbVotes";
    public static String RESPONSE = "Response";

    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String poster;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String response;

    //getters n setters
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getImdbID() { return imdbID; }

    public void setImdbID(String imdbID) { this.imdbID = imdbID; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getPoster() { return poster;}

    public void setPoster(String poster) {this.poster = poster;}

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
