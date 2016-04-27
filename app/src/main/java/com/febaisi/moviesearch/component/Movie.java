package com.febaisi.moviesearch.component;

/**
 * Created by felipebaisi on 4/27/16.
 */
public class Movie {

    public static String TITLE = "Title";
    public static String YEAR  = "Year";
    public static String IMDB_ID  = "imdbID";
    public static String TYPE  = "Type";
    public static String POSTER  = "Poster";


    String title;
    String year;
    String imdbID;
    String type;
    String poster;

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



}
