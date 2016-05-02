package com.febaisi.moviesearch.uicontent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.febaisi.moviesearch.util.MovieUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by felipebaisi on 4/28/16.
 */
public class MovieDetailsActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, MovieInfoController.ResultMovieInfoSearchListener {

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
        Movie movie = MovieUtil.createMovieFromIntent(intent);
        headerTopTitle.setText(movie.getTitle());
        headerTopYear.setText(movie.getYear());
        floatHeaderView.bindTo(movie.getTitle(), movie.getYear());
        Picasso.with(this).load(movie.getPoster()).into(poster);

        if (intent.getBooleanExtra(MovieController.REQUEST_MOVIE_INFO, false)) {
            movieInfoController.retrieveMovieInfo(movie.getImdbID());
            movieInfoController.setResultMovieInfoSearchListener(this);
        }
        updateViews(movie);
    }

    private void updateViews(Movie movie) {
        //add rows for each movie item
        if (movie.getPlot() != null) {
            summaryTextView.setText(movie.getPlot());
        }
        if ((movie.getActors() != null) && !(movie.getActors().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_group_grey_800_24dp,movie.getActors());
        }
        if ((movie.getDirector() != null) && !(movie.getDirector().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_videocam_grey_800_24dp, movie.getDirector());
        }
        if ((movie.getWriter() != null) && !(movie.getWriter().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_mode_edit_grey_800_24dp, movie.getWriter());
        }
        if ((movie.getReleased() != null) && !(movie.getReleased().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_date_range_grey_800_24dp, movie.getReleased());
        }
        if ((movie.getRuntime() != null) && !(movie.getRuntime().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_av_timer_grey_800_24dp, movie.getRuntime());
        }
        if ((movie.getGenre() != null) && !(movie.getGenre().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_movie_grey_800_24dp, movie.getGenre());
        }
        if ((movie.getMetascore() != null) && !(movie.getMetascore().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_star_grey_800_24dp, movie.getMetascore());
        }
        if ((movie.getAwards() != null) && !(movie.getAwards().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_star_border_grey_800_24dp, movie.getAwards());
        }
        if ((movie.getCountry() != null) && !(movie.getCountry().equals("N/A"))) {
            addLayoutRow(R.drawable.ic_location_on_grey_800_24dp, movie.getCountry());
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
    public void onMovieInfoResult(Movie movie) {
        updateViews(movie);
    }
}
