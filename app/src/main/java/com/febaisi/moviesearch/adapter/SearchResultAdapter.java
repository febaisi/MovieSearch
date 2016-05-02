package com.febaisi.moviesearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.component.CustomTextView;
import com.febaisi.moviesearch.controller.MovieInfoController;
import com.febaisi.moviesearch.model.Movie;
import com.febaisi.moviesearch.util.MovieUtil;
import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by felipe.baisi on 4/27/2016.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> implements MovieInfoController.ResultMovieInfoSearchListener {

    private List<Movie> mMoviesList;
    private Context mContext;

    public SearchResultAdapter(List<Movie> moviesList) {
        this.mMoviesList = moviesList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CustomTextView mTitleTextView;
        public CardView mCustomCardView;
        public TextView mPlotTextView;
        public SelectableRoundedImageView mSelectableRoundedImageView;
        public ImageView mShareImageView;
        public ViewHolder(View v) {
            super(v);
            mTitleTextView = (CustomTextView) v.findViewById(R.id.movie_title);
            mPlotTextView = (TextView) v.findViewById(R.id.movie_plot);
            mCustomCardView = (CardView) v.findViewById(R.id.cardView);
            mSelectableRoundedImageView = (SelectableRoundedImageView) v.findViewById(R.id.movie_poster);
            mShareImageView = (ImageView) v.findViewById(R.id.share_image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recycler_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        mContext = v.getContext();
        retrieveMoviesInfo();

        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTitleTextView.setText(mMoviesList.get(position).getTitle());
        holder.mCustomCardView.setPreventCornerOverlap(false);

        String plot = mMoviesList.get(position).getPlot();
        if (plot != null && plot.length() > 120) {
            plot = plot.substring(0,120) + "...";
        }
        holder.mPlotTextView.setText(plot);

        String imageUrl = mMoviesList.get(position).getPoster();
        if ((imageUrl != null) && !(imageUrl.isEmpty()) && !(imageUrl.equals("N/A"))) {
            Picasso.with(mContext).load(imageUrl).into(holder.mSelectableRoundedImageView);
        }
        holder.mCustomCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(MovieUtil.createMovieInfoIntent(mContext, mMoviesList.get(position), false));
            }
        });
        holder.mShareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareMessage(mMoviesList.get(position));
            }
        });
    }

    public int getItemCount() {
        return mMoviesList.size();
    }

    public void updateDataSet(List<Movie> moviesList) {
        this.mMoviesList = moviesList;
    }

    private void retrieveMoviesInfo(){
        MovieInfoController movieInfoController = new MovieInfoController(mContext);
        movieInfoController.setResultMovieInfoSearchListener(this);
        for (Movie movie : mMoviesList) {
            movieInfoController.retrieveMovieInfo(movie.getImdbID());
        }
    }

    @Override
    public void onMovieInfoResult(Movie movie) {
        for (Movie curMovieInfo : mMoviesList) {
            if(curMovieInfo.getImdbID().equals(movie.getImdbID())) {
                curMovieInfo.setPlot(movie.getPlot());
                curMovieInfo.setActors(movie.getActors());
                curMovieInfo.setDirector(movie.getDirector());
                curMovieInfo.setWriter(movie.getWriter());
                curMovieInfo.setReleased(movie.getReleased());
                curMovieInfo.setRuntime(movie.getRuntime());
                curMovieInfo.setGenre(movie.getGenre());
                curMovieInfo.setMetascore(movie.getMetascore());
                curMovieInfo.setAwards(movie.getAwards());
                curMovieInfo.setCountry(movie.getCountry());
                break;
            }
        }
        notifyDataSetChanged();
    }

    private void shareMessage(Movie movie) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        intent.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.share_title));
        intent.putExtra(Intent.EXTRA_TEXT, mContext.getResources().getString(R.string.share_description_title) + ". " + movie.getTitle()
                + " " + mContext.getResources().getString(R.string.share_description_summary) + " " + movie.getPlot());
        mContext.startActivity(intent);
    }
}
