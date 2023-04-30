package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MovieRepository {

    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Director> directors = new ArrayList<>();
    HashMap<Director, ArrayList<Movie>> pair = new HashMap<Director, ArrayList<Movie>>();

    public Boolean addMovie(Movie movie) {
        movies.add(movie);
        return true;
    }

    public Boolean addDirector(Director director) {
        directors.add(director);
        return true;
    }

    public Boolean addPair(Movie movie, Director director) {
        if(pair.containsKey(director))
            pair.get(director).add(movie);
        else{
            pair.put(director,new ArrayList<>());
            pair.get(director).add(movie);
        }
        return true;
    }

    public ArrayList<Movie> getDirectorMovies(Director director) {
        if(pair.containsKey(director))
            return pair.get(director);

        return new ArrayList<Movie>();
    }
}