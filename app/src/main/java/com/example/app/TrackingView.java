package com.example.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

// If the base class is changed from LinearLayout to something
// else the gc starts working.
public class TrackingView extends LinearLayout {
    static final private String TAG = TrackingView.class.getCanonicalName();

    public TrackingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    static int count = 0;
    public TrackingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.d(TAG, "++count=" + ++count);
    }

    @Override
    protected void finalize() throws Throwable {
        Log.d(TAG, "--count=" + --count);
        ((MainActivity) getContext()).gcSuccess(getTag().toString());
        super.finalize();
    }
}
