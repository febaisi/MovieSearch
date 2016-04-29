package com.febaisi.moviesearch.component;

/**
 * Created by felipebaisi on 4/28/16.
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.febaisi.moviesearch.R;

/**
 * Created by anton on 11/12/15.
 */

public class HeaderView extends LinearLayout {

    TextView name;
    TextView year;

    public HeaderView(Context context) {
        super(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        name = (TextView) findViewById(R.id.name);
        year = (TextView) findViewById(R.id.year);
    }

    public void bindTo(String name, String year) {
        this.name.setText(name);
        this.year.setText(year);
    }

    public void setTextSize(float size) {
        name.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }
}
