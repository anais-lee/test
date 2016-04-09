package edu.upenn.cis350.entertainmenttracker;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;


public class Movie implements Comparable<Movie>, Parcelable {
    String title,imdbID,poster;
    int year, rating;

    protected Movie()
    {
    }

    public Movie(JSONObject movieJSON) throws JSONException {
        Movie movie=new Movie();
        movie.setTitle(movieJSON.getString("Title"));
        movie.setYear(movieJSON.getString("Year"));
        movie.setImdbID(movieJSON.getString("imdbID"));
        movie.setPoster(movieJSON.getString("Poster"));
    }

    protected Movie(Parcel in) {
        title = in.readString();
        imdbID = in.readString();
        poster = in.readString();
        year = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = Integer.parseInt(year);
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }


    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Movie another) {
        int y= another.getYear();
        return y-this.getYear();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {

        return title;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imdbID);
        dest.writeString(poster);
        dest.writeInt(year);
    }
/*
    public static Movie getDetails(JSONObject jsonMovie) throws JSONException {
        Movie movie=new Movie();
        movie.setImdbID(jsonMovie.getString("imdbID"));

        return movie;
    }*/
}