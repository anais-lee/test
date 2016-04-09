package edu.upenn.cis350.entertainmenttracker;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by AngelaLee on 3/23/16.
 */
public class OfficialMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officialmovie);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/centurygothic.TTF");
        ((TextView) findViewById(R.id.header)).setTypeface(tf);

    }

}
