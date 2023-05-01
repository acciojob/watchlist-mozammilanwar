// package com.driver;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import org.springframework.stereotype.Repository;

// @Repository
// public class MovieRepository {
//     private HashMap<String, Movie> movieMap;
//     private HashMap<String, Director> directorMap;
//     private HashMap<String, List<String>> movieDirectorPairMap;

//     public MovieRepository() {
//         this.movieMap = new HashMap<String, Movie>();
//         this.directorMap = new HashMap<String, Director>();
//         this.movieDirectorPairMap = new HashMap<String, List<String>>();

//     }

//     public void movieAdd(Movie movie) {
//         movieMap.put(movie.getName(), movie);
//     }

//     public void directorAdd(Director director) {
//         directorMap.put(director.getName(), director);
//     }

//     public void movieDirectorPairAdd(String movieName, String directorName) {
//         List<String> pair = new ArrayList<>();
//         if (movieDirectorPairMap.containsKey(directorName)) {
//             pair = movieDirectorPairMap.get(directorName);
//         }
//         pair.add(movieName);
//         movieDirectorPairMap.put(directorName, pair);

//         // if (movieDirectorPairMap.containsKey(directorName)) {
//         // List<String> pair = movieDirectorPairMap.get(directorName);
//         // pair.add(movieName);
//         // movieDirectorPairMap.put(directorName, pair);
//         // }
//     }

//     public Movie findMovie(String movieName) {
//         return movieMap.get(movieName);
//     }

//     public Director findDirector(String name) {
//         return directorMap.get(name);
//     }

//     public List<String> moviesFromDirectorFind(String director) {
//         List<String> allMovie = new ArrayList<>();
//         if (movieDirectorPairMap.containsKey(director)) {
//             allMovie = movieDirectorPairMap.get(director);
//         }
//         return allMovie;
//     }

//     public List<String> allMoviesFind() {
//         return new ArrayList<>(movieMap.keySet());
//     }

//     public void removeDirector(String name) {
//         movieDirectorPairMap.remove(name);
//         directorMap.remove(name);
//     }

//     public void removeMovie(String movieName) {
//         movieMap.remove(movieName);
//     }

//     public void deleteAllDirector() {
//     }

//     public List<String> getAllDirector() {
//         return new ArrayList<>(directorMap.keySet());
//     }

// }


package com.driver;

import java.util.*;

public class MovieRepository {
    HashMap<String, ArrayList<String>> pair = new HashMap<>();
    HashMap<String,Movie> movies = new HashMap<>();
    HashMap<String,Director> directors = new HashMap<>();

    public boolean addMovie(Movie movie) {
        movies.put(movie.getName(),movie);
        return true;
    }

    public Optional<Movie> ischeckmovie(String moviename) {
        if(movies.containsKey(moviename)){
            return Optional.of(movies.get(moviename));
        }
        return Optional.empty();
    }

    public Optional<Director> ischeckdirector(String directorname) {
        if(directors.containsKey(directorname)){
            return Optional.of(directors.get(directorname));
        }
        return Optional.empty();
    }

    public boolean addDirector(Director director) {
        directors.put(director.getName(),director);
        return true;
    }

    public boolean addMovieDirectorPair(String moviename, String directorname) {
        if(pair.containsKey(directorname)){
            pair.get(directorname).add(moviename);
        }
        else{
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(moviename);
            pair.put(directorname,tmp);
        }
        return true;
    }

    public List<String> getMoviesByDirectorName(String name) {
        return pair.get(name);
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    public void deletedirector(String director) {
        directors.remove(director);
        pair.remove(director);
    }

    public void deletemovies(String x) {
        movies.remove(x);
    }

    public List<String> getallDirector() {
        return new ArrayList<>(directors.keySet());
    }
}