package com.febaisi.moviesearch.adapter;

import android.content.Context;
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
import com.febaisi.moviesearch.model.MovieInfo;
import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by felipe.baisi on 4/27/2016.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> implements MovieInfoController.ResultMovieInfoSearchListener {

    private List<MovieInfo> mMoviesInfoList;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CustomTextView mTitleTextView;
        public CardView mCustomCardView;
        public TextView mPlotTextView;
        public SelectableRoundedImageView mSelectableRoundedImageView;
        public ViewHolder(View v) {
            super(v);
            mTitleTextView = (CustomTextView) v.findViewById(R.id.movie_title);
            mPlotTextView = (TextView) v.findViewById(R.id.movie_plot);
            mCustomCardView = (CardView) v.findViewById(R.id.cardView);
            mSelectableRoundedImageView = (SelectableRoundedImageView) v.findViewById(R.id.movie_poster);
        }
    }

    public SearchResultAdapter(List<MovieInfo> listMovies) {
        this.mMoviesInfoList = listMovies;
    }

    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recycler_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        mContext = v.getContext();
        retrieveMoviesInfo();
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mMoviesInfoList.get(position).getTitle());
        holder.mCustomCardView.setPreventCornerOverlap(false);

        String plot = mMoviesInfoList.get(position).getPlot();
        if (plot != null && plot.length() > 74) {
            plot = plot.substring(0,80) + "...";
        }
        holder.mPlotTextView.setText(plot);

        String imageUrl = mMoviesInfoList.get(position).getPoster();
        if (!imageUrl.equals("N/A")) {
            Picasso.with(mContext).load(imageUrl).into(holder.mSelectableRoundedImageView);
        }


    }

    public int getItemCount() {
        return mMoviesInfoList.size();
    }

    public void updateDataSet(List<MovieInfo> moviesList) {
        this.mMoviesInfoList = moviesList;
    }

    private void retrieveMoviesInfo(){
        MovieInfoController movieInfoController = new MovieInfoController(mContext);
        movieInfoController.setResultMovieInfoSearchListener(this);
        for (MovieInfo movieInfo : mMoviesInfoList) {
            movieInfoController.retrieveMovieInfo(movieInfo.getImdbID());
        }
    }

    @Override
    public void onMovieInfoResult(MovieInfo movieInfo) {
        for (MovieInfo curMovieInfo : mMoviesInfoList) {
            if(curMovieInfo.getImdbID().equals(movieInfo.getImdbID())) {
                curMovieInfo.setPlot(movieInfo.getPlot());
                break;
            }
        }
        notifyDataSetChanged();
    }



}
