package edu.upenn.cis350.entertainmenttracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sydneymorton on 4/9/16.
 */
public class MovieRetriever {

    public static ArrayList<Movie> retrieveMovies(File file) {

        StringBuilder result = new StringBuilder();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                result.append(getStringResult(scanner.next()));
            }
        }
        catch(IOException e) {
            throw new RuntimeException("Error writing to movies.txt");
        }

        return parseMovies(result.toString());
    }

    protected static String getStringResult(String searchId) {

        URL url = null;
        String result = null;

        try {
            url = new URL("http://www.omdbapi.com/?type=movie&i="
                    + searchId);
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

            try {
                result = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    protected static ArrayList<Movie> parseMovies(String str) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try {
            JSONObject root = new JSONObject(str);
            JSONArray movieJSONArray = root.getJSONArray("Search");

            for (int i = 0; i < movieJSONArray.length(); i++) {
                JSONObject movieJSON = movieJSONArray.getJSONObject(i);
                Movie movie = new Movie(movieJSON);
                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }


}
