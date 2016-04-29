package com.febaisi.moviesearch.uicontent;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.component.CustomTextView;
import com.febaisi.moviesearch.component.HeaderView;
import com.febaisi.moviesearch.controller.MovieController;
import com.febaisi.moviesearch.controller.MovieInfoController;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.model.MovieInfo;
import com.febaisi.moviesearch.util.MovieUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by felipebaisi on 4/28/16.
 */
public class MovieInfoActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, MovieInfoController.ResultMovieInfoSearchListener {

    private HeaderView toolbarHeaderView;
    private HeaderView floatHeaderView;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CustomTextView summaryTextView;
    private TextView headerTopTitle;
    private TextView headerTopYear;
    private ImageView poster;
    private LinearLayout rowLayouContent;
    private MovieInfoController movieInfoController;

    private boolean isHideToolbarView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing_movie);

        //gettings views
        toolbarHeaderView = (HeaderView) findViewById(R.id.toolbar_header_view);
        floatHeaderView = (HeaderView) findViewById(R.id.float_header_view);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        summaryTextView = (CustomTextView) findViewById(R.id.movie_info_summary);
        headerTopTitle = (TextView) findViewById(R.id.header_view_top_title);
        headerTopYear = (TextView) findViewById(R.id.header_view_top_year);
        poster = (ImageView) findViewById(R.id.collapse_poster);
        rowLayouContent = (LinearLayout) findViewById(R.id.movie_info_row_content);


        //Add action bar settings
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appBarLayout.addOnOffsetChangedListener(this);

        movieInfoController = new MovieInfoController(this);
        handleIntent(getIntent());
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

    private void handleIntent(Intent intent) {
        MovieInfo movieInfo = MovieUtil.createMovieInfoFromIntent(intent);
        headerTopTitle.setText(movieInfo.getTitle());
        headerTopYear.setText(movieInfo.getYear());
        floatHeaderView.bindTo(movieInfo.getTitle(), movieInfo.getYear());
        Picasso.with(this).load(movieInfo.getPoster()).into(poster);

        if (intent.getBooleanExtra(MovieController.REQUEST_MOVIE_INFO, false)) {
            movieInfoController.retrieveMovieInfo(movieInfo.getImdbID());
            movieInfoController.setResultMovieInfoSearchListener(this);
        }
        updateViews(movieInfo);
    }

    private void updateViews(MovieInfo movieInfo) {
        //add rows for each movie item
        if (movieInfo.getPlot() != null) {
            summaryTextView.setText(movieInfo.getPlot());
        }
        if ((movieInfo.getActors() != null) && !(movieInfo.getActors().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_group_grey_800_24dp,movieInfo.getActors());
        }
        if ((movieInfo.getDirector() != null) && !(movieInfo.getDirector().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_videocam_grey_800_24dp, movieInfo.getDirector());
        }
        if ((movieInfo.getWriter() != null) && !(movieInfo.getWriter().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_mode_edit_grey_800_24dp, movieInfo.getWriter());
        }
        if ((movieInfo.getReleased() != null) && !(movieInfo.getReleased().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_date_range_grey_800_24dp, movieInfo.getReleased());
        }
        if ((movieInfo.getRuntime() != null) && !(movieInfo.getRuntime().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_av_timer_grey_800_24dp, movieInfo.getRuntime());
        }
        if ((movieInfo.getGenre() != null) && !(movieInfo.getGenre().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_movie_grey_800_24dp, movieInfo.getGenre());
        }
        if ((movieInfo.getMetascore() != null) && !(movieInfo.getMetascore().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_star_grey_800_24dp, movieInfo.getMetascore());
        }
        if ((movieInfo.getAwards() != null) && !(movieInfo.getAwards().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_star_border_grey_800_24dp, movieInfo.getAwards());
        }
        if ((movieInfo.getCountry() != null) && !(movieInfo.getCountry().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_location_on_grey_800_24dp, movieInfo.getCountry());
        }
    }

    private void addLayoutRow(int drawableRes, String itemDescription){
        View movieInfoRow =  getLayoutInflater().inflate(R.layout.move_info_row, null);
        TextView textView = (TextView) movieInfoRow.findViewById(R.id.movie_info_item_detail);
        ImageView imageView = (ImageView) movieInfoRow.findViewById(R.id.movie_info_item_image);
        textView.setText(itemDescription);
        imageView.setImageDrawable(getResources().getDrawable(drawableRes, null));
        rowLayouContent.addView(movieInfoRow);
    }

    @Override
    public void onMovieInfoResult(MovieInfo movieInfo) {
        updateViews(movieInfo);
    }
}
