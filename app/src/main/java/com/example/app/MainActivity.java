package com.example.app;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.util.logging.Handler;

public class MainActivity extends ActionBarActivity {

    private boolean gcSuccessLeft = false;
    private boolean gcSuccessRight = false;

    public void gcSuccess(String layoutTag) {
        if (layoutTag.equals("no_gc")) {
            gcSuccessLeft = true;
        } else if (layoutTag.equals("yes_gc")) {
            gcSuccessRight = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Fragment leftGcFragment = new PlaceholderFragment(R.layout.no_gc);
        final Fragment rightGcFragment = new PlaceholderFragment(R.layout.yes_gc);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.left_container, leftGcFragment)
                .add(R.id.right_container, rightGcFragment)
                .commit();
        findViewById(R.id.container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .remove(leftGcFragment)
                        .remove(rightGcFragment)
                        .commit();

            }
        });
        checkStatus();
    }

    private void checkStatus() {
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (gcSuccessLeft) {
                    findViewById(R.id.left_container).setBackgroundColor(Color.GREEN);
                }
                if (gcSuccessRight) {
                    findViewById(R.id.right_container).setBackgroundColor(Color.GREEN);
                }
                System.gc();
                checkStatus();
            }
        }, 200);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        final int layoutId;
        public PlaceholderFragment(int layoutId) {
            this.layoutId = layoutId;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(layoutId, container, false);
        }

        @Override
        public void onDestroyView() {
            Log.d(PlaceholderFragment.class.getCanonicalName(), "onDestroyView");
            super.onDestroyView();
        }
    }

}
