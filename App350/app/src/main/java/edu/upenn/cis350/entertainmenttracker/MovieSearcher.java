package edu.upenn.cis350.entertainmenttracker;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Elsie on 3/27/16.
 */
public class MovieSearcher  {

    String stringResult;
    ArrayList<Movie> movies;

    public MovieSearcher() {
    }
/*
    public MovieSearcher(IMovies activity) {
        this.activity = activity;
    }*/

    public ArrayList<Movie> search(String searchTerm) {
        stringResult = getStringResult(searchTerm);
        movies = parseMovies(stringResult);
        return movies;
    }

    protected String getStringResult(String searchTerm) {
        URL url = null;
        try {
            url = new URL("http://www.omdbapi.com/?type=movie&s=" + searchTerm);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        try {
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int statusCode = 0;
        try {
            statusCode = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (statusCode == HttpURLConnection.HTTP_OK) {

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            StringBuilder result = new StringBuilder();
            String line = null;

            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (line != null) {
                result.append(line);
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result.toString();
        }
        return null;
    }

    protected ArrayList<Movie> parseMovies(String str)  {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
       try {
            JSONObject root = new JSONObject(str);
            JSONArray movieJSONArray = root.getJSONArray("Search");

            for (int i = 0; i < movieJSONArray.length(); i++) {
                JSONObject movieJSON = movieJSONArray.getJSONObject(i);
                Movie movie = new Movie(movieJSON);
                movieList.add(movie);
            }
        }  catch (JSONException e)
       {
           e.printStackTrace();
       }
        return movieList;
    }
}


