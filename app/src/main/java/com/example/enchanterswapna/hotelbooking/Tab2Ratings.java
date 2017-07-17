package com.example.enchanterswapna.hotelbooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by enchanterswapna on 7/7/17.
 */

public class Tab2Ratings extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2ratings, container, false);

        ProgressBar progressBar1 = (ProgressBar)rootView.findViewById (R.id.circularProgressBar);

        progressBar1.setProgress(30);

// as 60 is max, we specified in the xml layout, 30 will be its half 😉
        ((TextView)rootView.findViewById(R.id.textview1)).setText("50%"); // The      text inside the circular progressbar
// change it programmatically

        TextView t1=(TextView)rootView.findViewById(R.id.tx1);
        t1.setText("Hotels.com");
        TextView t2=(TextView)rootView.findViewById(R.id.tx2);
        t1.setText("(50)");
        TextView t3=(TextView)rootView.findViewById(R.id.tx3);
        t1.setText("Holidatcheck");
        TextView t4=(TextView)rootView.findViewById(R.id.tx4);
        t1.setText("(7)");
        return rootView;
    }
}
