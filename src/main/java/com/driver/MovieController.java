package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {
    MovieService movieService = new MovieService();
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        Boolean added = movieService.addMovie(movie);
        if(added)
            return new ResponseEntity("success" , HttpStatus.OK);

        return new ResponseEntity(HttpStatus.valueOf(500));
    }


    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        Boolean added = movieService.addDirector(director);
        if(added)
            return new ResponseEntity("success" , HttpStatus.OK);

        return new ResponseEntity( HttpStatus.valueOf(500));
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName ){
        Boolean added = movieService.addPair(movieName, directorName);
        if(added)
            return new ResponseEntity("success", HttpStatus.OK);

        return new ResponseEntity(HttpStatus.valueOf(500));
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        Optional<Movie> optionalMovie = movieService.getMovie(name);
        if(optionalMovie.isPresent())
            return new ResponseEntity(optionalMovie.get(), HttpStatus.OK);

        return new ResponseEntity( HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-director/by/name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        Optional<Director> optionalDirector = movieService.getDirector(name);
        if(optionalDirector.isPresent())
            return  new ResponseEntity(optionalDirector.get(), HttpStatus.OK);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director){
        return new ResponseEntity(movieService.getDirectorMovies(director), HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        return new ResponseEntity(movieService.getAllMovies(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name){
        Boolean removed = movieService.deleteDirectorMovies(name);
        if(removed)
            return new ResponseEntity("success" , HttpStatus.OK);

        return new ResponseEntity( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        movieService.deleteAllDirectorMovies();
        return new ResponseEntity("success" , HttpStatus.OK);
    }
}