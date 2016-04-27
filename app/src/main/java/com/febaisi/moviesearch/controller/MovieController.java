package com.febaisi.moviesearch.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.febaisi.moviesearch.R;
import com.febaisi.moviesearch.VolleyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by felipebaisi on 4/27/16.
 */
public class MovieController {

    public static String[] COLUMS = new String[]{BaseColumns._ID, "Title"};
    public static String JSON_REQUEST_TAG = "SUGGESTION_REQUEST";
    private Context mContext;

    //Implement interface
    private Searchable mSearchable;
    public interface Searchable {
        void notifyAdapter(Cursor cursor);
    }

    public MovieController (Context context, Searchable searchable){
        this.mContext = context;
        this.mSearchable = searchable;
    }

    public void retrieveData(String query) {
        String url = "http://www.omdbapi.com/?s=" + query;

        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        MatrixCursor matrixCursor = new MatrixCursor(COLUMS);
                        try {
                            JSONArray searchArray = response.getJSONArray("Search");
                            for(int i = 0; i<searchArray.length(); i++){
                                matrixCursor.addRow(new String[]{Integer.toString(i),
                                        searchArray.getJSONObject(i).getString(COLUMS[1])});
                            }
                        } catch (JSONException e) {
                            Log.e(mContext.getResources().getString(R.string.app_name), e.toString());
                        }

                        if (mSearchable != null) {
                            mSearchable.notifyAdapter(matrixCursor);
                        }

                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(mContext.getResources().getString(R.string.app_name), error.toString());
                    }
                }
        );

        request.setTag(JSON_REQUEST_TAG);
        VolleyApplication.getInstance().getRequestQueue().cancelAll(JSON_REQUEST_TAG);
        VolleyApplication.getInstance().getRequestQueue().add(request);


    }
}
