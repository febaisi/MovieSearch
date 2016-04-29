package com.febaisi.moviesearch.uicontent;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.component.HeaderView;

/**
 * Created by felipebaisi on 4/28/16.
 */
public class MovieInfoActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    protected HeaderView toolbarHeaderView;
    protected HeaderView floatHeaderView;
    protected AppBarLayout appBarLayout;
    protected Toolbar toolbar;

    private boolean isHideToolbarView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_result);

        toolbarHeaderView = (HeaderView) findViewById(R.id.toolbar_header_view);
        floatHeaderView = (HeaderView) findViewById(R.id.float_header_view);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appBarLayout.addOnOffsetChangedListener(this);


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
}
