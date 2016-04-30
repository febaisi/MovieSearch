package com.febaisi.moviesearch.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.component.CustomTextView;
import com.febaisi.moviesearch.controller.MovieInfoController;
import com.febaisi.moviesearch.model.MovieInfo;
import com.febaisi.moviesearch.uicontent.ResultMovieFragment;
import com.febaisi.moviesearch.util.MovieUtil;
import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by felipe.baisi on 4/27/2016.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> implements MovieInfoController.ResultMovieInfoSearchListener {

    private List<MovieInfo> mMoviesInfoList;
    private Context mContext;
    public int currentRows = 0;
    MovieInfoController movieInfoController;

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
        movieInfoController = new MovieInfoController(mContext);
        movieInfoController.setResultMovieInfoSearchListener(this);
    }

    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recycler_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        mContext = v.getContext();
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTitleTextView.setText(mMoviesInfoList.get(position).getTitle());
        holder.mCustomCardView.setPreventCornerOverlap(false);

        String plot = mMoviesInfoList.get(position).getPlot();
        if (plot != null && plot.length() > 120) {
            plot = plot.substring(0,120) + "...";
        }
        holder.mPlotTextView.setText(plot);

        String imageUrl = mMoviesInfoList.get(position).getPoster();
        if ((imageUrl != null) && !(imageUrl.isEmpty()) && !(imageUrl.equals("N/A"))) {
            Picasso.with(mContext).load(mMoviesInfoList.get(position).getPoster()).into(holder.mSelectableRoundedImageView);
        } else {
            holder.mSelectableRoundedImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bck_rounded, null));
        }
        holder.mCustomCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(MovieUtil.createMovieInfoIntent(mContext, mMoviesInfoList.get(position), false));
            }
        });
    }

    public int getItemCount() {
        return currentRows;
    }

    public void updateDataSet(List<MovieInfo> moviesList) {
        this.mMoviesInfoList = moviesList;
        for (MovieInfo movieInfo : moviesList) {
            movieInfoController.retrieveMovieInfo(movieInfo.getImdbID());
        }
    }

    @Override
    public void onMovieInfoResult(MovieInfo movieInfo) {
        for (MovieInfo curMovieInfo : mMoviesInfoList) {
            if(curMovieInfo.getImdbID().equals(movieInfo.getImdbID())) {
                curMovieInfo.setPlot(movieInfo.getPlot());
                curMovieInfo.setActors(movieInfo.getActors());
                curMovieInfo.setDirector(movieInfo.getDirector());
                curMovieInfo.setWriter(movieInfo.getWriter());
                curMovieInfo.setReleased(movieInfo.getReleased());
                curMovieInfo.setRuntime(movieInfo.getRuntime());
                curMovieInfo.setGenre(movieInfo.getGenre());
                curMovieInfo.setMetascore(movieInfo.getMetascore());
                curMovieInfo.setAwards(movieInfo.getAwards());
                curMovieInfo.setCountry(movieInfo.getCountry());
                break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onMovieResultFail() {
    }

    public void setCurrentRows(int currentRows) {
        this.currentRows = currentRows;
    }

}
