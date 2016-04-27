package com.febaisi.moviesearch.uicontent;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.adapter.SearchResultAdapter;
import com.febaisi.moviesearch.controller.MovieController;

/**
 * Created by felipe.baisi on 4/27/2016.
 */
public class ResultContentFragment extends Fragment {

    public static String QUERY = "QUERY";
    private String mTitleQuery;
    private MovieController movieController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mTitleQuery = bundle.getString(QUERY);
        }
        return inflater.inflate(R.layout.content_card_result, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_result);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //SearchResultAdapter adapter = new SearchResultAdapter(new String[]{"fef111o", "fef2o2", "fefo3", "fefo4", "fefo2", "fefo3", "fefo4", "fefo2", "fefo3", "fefo4"} );
        //recyclerView.setAdapter(adapter);
    }
}
