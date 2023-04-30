package com.driver;

import java.util.HashSet;
import java.util.Optional;
import java.util.ArrayList;

public class MovieService {
    MovieRepository  movieRepository = new MovieRepository();

    public Boolean addMovie(Movie movie) {
        Optional<Movie> optionalMovie = getMovie(movie.getName());
        if(optionalMovie.isPresent())
            return false;

        return movieRepository.addMovie(movie);
    }

    public Boolean addDirector(Director director) {
        Optional<Director> optionalDirector = getDirector(director.getName());
        if(optionalDirector.isPresent())
            return false;

        return movieRepository.addDirector(director);
    }

    public Optional<Movie> getMovie(String name) {
        for(Movie movie : movieRepository.movies) {
            if (movie.getName().equals(name))
                return Optional.of(movie);
        }
        return Optional.empty();
    }

    public Optional<Director> getDirector(String name) {
        for(Director director : movieRepository.directors){
            if(director.getName().equals(name))
                return Optional.of(director);
        }
        return Optional.empty();
    }

    public ArrayList<Movie> getDirectorMovies(String director) {
        for(Director d : movieRepository.directors){
            if(d.getName().equals(director))
                return movieRepository.getDirectorMovies(d);
        }
        return new ArrayList<Movie>();
    }

    public Boolean addPair(String movieName, String directorName) {
        Optional<Director> optionalDirector = getDirector(directorName);
        Optional<Movie> optionalMovie = getMovie(movieName);

        if(optionalMovie.isPresent() && optionalDirector.isPresent())
            return movieRepository.addPair(optionalMovie.get() , optionalDirector.get());

        return false;
    }

    public ArrayList<Movie> getAllMovies() {
        return movieRepository.movies;
    }

    public Boolean deleteDirectorMovies(String name) {
        for(Director d : movieRepository.directors){
            if(d.getName().equals(name)){
                movieRepository.directors.remove(d);
                if(movieRepository.pair.containsKey(d)) {
                    HashSet<Movie> hs = new HashSet<>();
                    for(Movie m : movieRepository.pair.get(d))
                        hs.add(m);

                    for(Movie m : movieRepository.movies){
                        if(hs.contains(m))
                            movieRepository.movies.remove(m);
                    }
                    movieRepository.pair.remove(d);
                }
                return true;
            }
        }
        return false;
    }

    public void deleteAllDirectorMovies() {
        for(Director d : movieRepository.directors)
            deleteDirectorMovies(d.getName());
    }
}