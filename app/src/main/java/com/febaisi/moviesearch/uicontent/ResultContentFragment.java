package com.febaisi.moviesearch.uicontent;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.febaisi.moviesearch.R;

/**
 * Created by felipe.baisi on 4/27/2016.
 */
public class ResultContentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_card_result, container, false);
    }

}
