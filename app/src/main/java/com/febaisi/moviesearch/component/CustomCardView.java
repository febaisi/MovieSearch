package com.febaisi.moviesearch.component;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.controller.MovieInfoController;
import com.febaisi.moviesearch.model.MovieInfo;

/**
 * Created by felipebaisi on 4/28/16.
 */
public class CustomCardView extends CardView implements MovieInfoController.ResultMovieInfoSearchListener {

    private MovieInfoController mMovieInfoController;
    private String mImdbId;

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMovieInfoController = new MovieInfoController(context);
        mMovieInfoController.setResultMovieInfoSearchListener(this);
    }

    public void setmImdbId(String mImdbId) {
        this.mImdbId = mImdbId;
        mMovieInfoController.retrieveMovieInfo(mImdbId);
    }

    @Override
    public void onMovieInfoResult(MovieInfo movieInfo) {
        TextView plotTextView = (TextView) findViewById(R.id.movie_plot);
        String moviePlot = movieInfo.getPlot();
        if (moviePlot.length() > 74) {
            moviePlot = moviePlot.substring(0,74) + "...";
        }
        plotTextView.setText(moviePlot); //follow zeplin guide
        Log.i("Baisi", "Baisi " + moviePlot);
    }
}
