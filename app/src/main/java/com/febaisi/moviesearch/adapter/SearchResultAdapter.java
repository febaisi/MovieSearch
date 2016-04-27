package com.febaisi.moviesearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.component.Movie;

import java.util.List;

/**
 * Created by felipe.baisi on 4/27/2016.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<Movie> mListMovies;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.movie_title);
        }
    }

    public SearchResultAdapter(List<Movie> listMovies) {
        this.mListMovies = listMovies;
    }

    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recycler_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mListMovies.get(position).getTitle());
    }

    public int getItemCount() {
        return mListMovies.size();
    }
}
