package edu.upenn.cis350.entertainmenttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upenn.cis350.entertainmenttracker.datamanagement.RateSorter;
import edu.upenn.cis350.entertainmenttracker.datamanagement.TitleSorter;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Movie> movies;
    private String sortMethod = "RATING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<Movie>();
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/centurygothic.TTF");
        ((TextView) findViewById(R.id.header)).setTypeface(tf);

        updateState();
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

    public void updateState() {
        switch (sortMethod) {
            case "TITLE":
                findViewById(R.id.buttonTitle).setBackgroundResource(R.drawable.ssfilled_left);
                findViewById(R.id.buttonRating).setBackgroundResource(R.drawable.searchsort);
                findViewById(R.id.buttonDate).setBackgroundResource(R.drawable.searchsort_right);
                break;
            case "RATING":
                findViewById(R.id.buttonTitle).setBackgroundResource(R.drawable.searchsort_left);
                findViewById(R.id.buttonRating).setBackgroundResource(R.drawable.ssfilled);
                findViewById(R.id.buttonDate).setBackgroundResource(R.drawable.searchsort_right);
                break;
            case "DATE":
                findViewById(R.id.buttonTitle).setBackgroundResource(R.drawable.searchsort_left);
                findViewById(R.id.buttonRating).setBackgroundResource(R.drawable.searchsort);
                findViewById(R.id.buttonDate).setBackgroundResource(R.drawable.ssfilled_right);
                break;
        }


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
        Intent intent = new Intent(this, SearchActivity.class);
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void onWatchedClick(View view) {}

    public void onToWatchClick(View view) {}


    //sort the movies based on the different data when certain button is clicked

//    public void onGenreClick(View view) {
//        watchedMovies = GenreSorter.insertSort(watchedMovies);
//    }

    public void onDateClick(View view) {
        //movies = YearSorter.insertSort(movies);
        sortMethod = "DATE";
        updateState();
    }

    public void onTitleClick(View view) {
        movies = TitleSorter.insertSort(movies);
        sortMethod = "TITLE";
        updateState();
    }

    public void onRateClick(View view) {
        movies = RateSorter.insertSort(movies);
        sortMethod = "RATING";
        updateState();
    }
}
