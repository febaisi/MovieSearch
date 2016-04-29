package com.febaisi.moviesearch.uicontent;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.component.CustomTextView;
import com.febaisi.moviesearch.component.HeaderView;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.model.MovieInfo;
import com.squareup.picasso.Picasso;

/**
 * Created by felipebaisi on 4/28/16.
 */
public class MovieInfoActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private HeaderView toolbarHeaderView;
    private HeaderView floatHeaderView;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CustomTextView summaryTextView;
    private TextView headerTopTitle;
    private TextView headerTopYear;
    private ImageView poster;
    private LinearLayout rowLayouContent;

    private boolean isHideToolbarView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing_movie);

        toolbarHeaderView = (HeaderView) findViewById(R.id.toolbar_header_view);
        floatHeaderView = (HeaderView) findViewById(R.id.float_header_view);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        summaryTextView = (CustomTextView) findViewById(R.id.movie_info_summary);
        headerTopTitle = (TextView) findViewById(R.id.header_view_top_title);
        headerTopYear = (TextView) findViewById(R.id.header_view_top_year);
        poster = (ImageView) findViewById(R.id.collapse_poster);
        rowLayouContent = (LinearLayout) findViewById(R.id.movie_info_row_content);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appBarLayout.addOnOffsetChangedListener(this);

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

        String title = "";
        String year = "";

        if ((intent.hasExtra(MovieInfo.TITLE)) && (intent.getStringExtra(MovieInfo.TITLE) != null) && !(intent.getStringExtra(MovieInfo.TITLE).equals("N/A"))) {
            title = getIntent().getStringExtra(MovieInfo.TITLE);
            headerTopTitle.setText(title);
        }
        if ((intent.hasExtra(MovieInfo.YEAR)) && (intent.getStringExtra(MovieInfo.YEAR) != null) && !(intent.getStringExtra(MovieInfo.YEAR).equals("N/A"))) {
            year = getIntent().getStringExtra(MovieInfo.YEAR);
            headerTopYear.setText(year);
        }
        floatHeaderView.bindTo(title, year);
        if (intent.hasExtra(MovieInfo.PLOT)) {
            summaryTextView.setText(intent.getStringExtra(MovieInfo.PLOT));
        }
        if (intent.hasExtra(MovieInfo.POSTER)) {
            Picasso.with(this).load(intent.getStringExtra(MovieInfo.POSTER)).into(poster);
        }

        //rows
        if ((intent.hasExtra(MovieInfo.ACTORS)) && (intent.getStringExtra(MovieInfo.ACTORS) != null) && !(intent.getStringExtra(MovieInfo.ACTORS).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_group_grey_800_24dp, intent.getStringExtra(MovieInfo.ACTORS));
        }
        if ((intent.hasExtra(MovieInfo.DIRECTOR)) && (intent.getStringExtra(MovieInfo.DIRECTOR) != null) && !(intent.getStringExtra(MovieInfo.DIRECTOR).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_movie_grey_800_24dp, intent.getStringExtra(MovieInfo.DIRECTOR));
        }
        if ((intent.hasExtra(MovieInfo.WRITER)) && (intent.getStringExtra(MovieInfo.WRITER) != null) && !(intent.getStringExtra(MovieInfo.WRITER).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_mode_edit_grey_800_24dp, intent.getStringExtra(MovieInfo.WRITER));
        }
        if ((intent.hasExtra(MovieInfo.RELEASED)) && (intent.getStringExtra(MovieInfo.RELEASED) != null) && !(intent.getStringExtra(MovieInfo.RELEASED).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_date_range_grey_800_24dp, intent.getStringExtra(MovieInfo.RELEASED));
        }
        if ((intent.hasExtra(MovieInfo.RUNTIME)) && (intent.getStringExtra(MovieInfo.RUNTIME) != null) && !(intent.getStringExtra(MovieInfo.RUNTIME).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_av_timer_grey_800_24dp, intent.getStringExtra(MovieInfo.RUNTIME));
        }
        if ((intent.hasExtra(MovieInfo.GENRE)) && (intent.getStringExtra(MovieInfo.GENRE) != null) && !(intent.getStringExtra(MovieInfo.GENRE).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_movie_grey_800_24dp, intent.getStringExtra(MovieInfo.GENRE));
        }
        if ((intent.hasExtra(MovieInfo.METASCORE)) && (intent.getStringExtra(MovieInfo.METASCORE) != null) && !(intent.getStringExtra(MovieInfo.METASCORE).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_star_grey_800_24dp, intent.getStringExtra(MovieInfo.METASCORE));
        }
        if ((intent.hasExtra(MovieInfo.AWARDS)) && (intent.getStringExtra(MovieInfo.AWARDS) != null) && !(intent.getStringExtra(MovieInfo.AWARDS).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_star_border_grey_800_24dp, intent.getStringExtra(MovieInfo.AWARDS));
        }
        if ((intent.hasExtra(MovieInfo.COUNTRY)) && (intent.getStringExtra(MovieInfo.COUNTRY) != null) && !(intent.getStringExtra(MovieInfo.COUNTRY).equals("N/A"))) {
            addLayoutRow(R.drawable.ic_location_on_grey_800_24dp, intent.getStringExtra(MovieInfo.COUNTRY));
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


}
