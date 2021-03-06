package edu.upenn.cis350.entertainmenttracker.datamanagement;

import java.util.ArrayList;

import edu.upenn.cis350.entertainmenttracker.Movie;

/**
 * Created by sydneymorton on 4/9/16.
 */
public class RatingSorter {
    // insertion sort for ArrayList
    public static ArrayList<Movie> insertSort(ArrayList<Movie> source){

        int index = 1;

        while (index < source.size()){
            insertSorted(source.get(index), source, index);
            index = index + 1;
        }
        return source;
    }

    // insert the given (movie) object into the given list
    // assuming elements 0 through index - 1 are sorted
    // use comp for comparison
    public static ArrayList insertSorted(Movie m, ArrayList<Movie> source, int index){
        int loc = index - 1;
        while ((loc >= 0) &&
                // c is smaller that the next highest element
                (m.getRating() <= source.get(loc).getRating())){
            // move element to the right
            source.set(loc + 1, source.get(loc));
            loc = loc - 1;
        }
        source.set(loc+1, m);
        return source;
    }
}
