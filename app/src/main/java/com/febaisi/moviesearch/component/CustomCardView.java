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

/**
 * Created by felipebaisi on 4/28/16.
 */
public class CustomCardView extends CardView  {

    private MovieInfoController mMovieInfoController;
    private String mImdbId;
    //public String getmImdbId() { return mImdbId; }
    public void setmImdbId(String mImdbId) {
        this.mImdbId = mImdbId;
         mMovieInfoController.retrieveMovieInfo(mImdbId);
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMovieInfoController = new MovieInfoController(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.movie_plot);
        textView.setText("Ae oe");

    }
}
