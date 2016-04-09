package edu.upenn.cis350.entertainmenttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.Button;

public class AddMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/centurygothic.TTF");
        ((TextView) findViewById(R.id.header)).setTypeface(tf);
        ((Button) findViewById(R.id.switchToWatch)).setTypeface(tf);
        ((Button) findViewById(R.id.switchWatched)).setTypeface(tf);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * ON CLICK FUNCTIONS
     * @param view
     */
    public void onMovieClick(View view) {

        //create a new intent with this class and the new
        //activity class
        Intent i = new Intent(this, AddMovie.class);
        startActivity(i);
    }


    public void onSearchClick(View view) {

    }

    public void onWatchedClick(View view) {}

    public void onToWatchClick(View view) {}

}
