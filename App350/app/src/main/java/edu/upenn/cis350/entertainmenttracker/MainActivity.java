package edu.upenn.cis350.entertainmenttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ArrayList<Movie> movies;
    private File savedMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //searchBar = (EditText) findViewById(R.id.searchBar);
        // Instantiate a ViewPager and a PagerAdapter.
        createExternalStoragePrivateFile();
        movies = MovieRetriever.retrieveMovies(savedMovies);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
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

    public void onSearchButtonClick(View v) {
            Intent intent = new Intent(this, SearchActivity.class);
            finish();
            startActivity(intent);
    }
/*
    public void onMovieClick(View view) {

        //create a new intent with this class and the new
        //activity class
        Intent i = new Intent(this, MovieActivity.class);
        startActivity(i);
    }


    public void onSearchButtonClick(View v) {
        searchTerm = searchBar.getText().toString().trim();

        if (searchTerm.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter a movie name", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra("searchTerm", searchTerm);
            finish();
            startActivity(intent);
        }
    }

    //sort the movies based on the different data when certain button is clicked

    public void onYearClick(View view) {
    }

    public void onTitleClick(View view) {
        movies = TitleSorter.insertSort(movies);
    }

    public void onRateClick(View view) {
        movies = RateSorter.insertSort(movies);
    } */

    private void createExternalStoragePrivateFile() {
        // Create a path where we will place our private file on external
        // storage.
        this.savedMovies = new File(getExternalFilesDir(null), "movies.txt");
    }

    private boolean hasExternalStoragePrivateFile() {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        this.savedMovies = new File(getExternalFilesDir(null), "movies.txt");
        if (savedMovies != null) {
            return savedMovies.exists();
        }
        return false;
    }

    /**
     * A simple pager adapter that represents movie objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return movies.size();
        }
    }
}
