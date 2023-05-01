// package com.driver;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// @RestController
// @RequestMapping("/movies")
// public class MovieController {

//     // @Autowired
//     // MovieService movieService;
//     MovieService movieService = new MovieService();

//     @PostMapping("/add-movie")
//     public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
//         movieService.movieAdded(movie);
//         return new ResponseEntity<String>("Movie added successfully", HttpStatus.CREATED);
//     }

//     @PostMapping("add-director")
//     public ResponseEntity<String> addDirector(@RequestBody Director director) {
//         movieService.directorAdded(director);
//         return new ResponseEntity<String>("Director added successfully", HttpStatus.CREATED);
//     }

//     @PutMapping("/add-movie-director-pair")
//     public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName) {
//         movieService.movieDirectorPairAdded(movieName, directorName);
//         return new ResponseEntity<>("Movie director pair added successfully", HttpStatus.CREATED);
//     }

//     @GetMapping("/get-movie-by-name/{name}")
//     public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
//         Movie movie = movieService.movidFind(name);
//         return new ResponseEntity<Movie>(movie, HttpStatus.OK);
//     }

//     @GetMapping("/movies/get-director-by-name/{name}")
//     public ResponseEntity<Director> getDirectorByName(@PathVariable String name) {
//         Director director = movieService.directorFind(name);
//         return new ResponseEntity<Director>(director, HttpStatus.OK);
//     }

//     @GetMapping("/get-movies-by-director-name/{director}")
//     public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) {
//         List<String> list = movieService.findMoviesFromDirector(director);
//         return new ResponseEntity<List<String>>(list, HttpStatus.OK);
//     }

//     @GetMapping("/get-all-movies")
//     public ResponseEntity<List<String>> findAllMovies() {
//         List<String> list = movieService.findMoviesAll();
//         return new ResponseEntity<List<String>>(list, HttpStatus.OK);
//     }

//     @DeleteMapping("/delete-director-by-name")
//     public ResponseEntity<String> deleteDirectorByName(@RequestParam String name) {
//         movieService.deleteDirector_ByName(name);
//         return new ResponseEntity<>("Director name deleted successfully", HttpStatus.OK);
//     }

//     @DeleteMapping("/delete-all-directors")
//     public ResponseEntity<String> deleteAllDirectors() {
//         movieService.allDeleteDirector();
//         return new ResponseEntity<>("Delete all directors successfully", HttpStatus.OK);
//     }

// }

package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    MovieService service = new MovieService();

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        try {
            boolean add = service.addMovie(movie);
            return new ResponseEntity<>("Added Successfully", HttpStatus.CREATED);
        } catch (movieAlredyPresentException e) {
            return new ResponseEntity<>("Failed to add movie", HttpStatus.valueOf(400));
        }
    }

    @PostMapping("/movies/add-Director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        try {
            boolean add = service.addDirector(director);
            return new ResponseEntity<>("Added Successfully", HttpStatus.CREATED);
        } catch (directorAlreadyPresent e) {
            return new ResponseEntity<>("Failed to add director", HttpStatus.valueOf(400));
        }
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam(required = true) String moviename,
                                                       @RequestParam(required = true) String directorname) {
        try {
            boolean add = service.addMovieDirectorPair(moviename, directorname);
            return new ResponseEntity<>("Added Successfully", HttpStatus.CREATED);
        } catch (MovieOrDirectornotpresentException e) {
            return new ResponseEntity<>("Failed to add director", HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        Movie movie = service.getMovieByName(name);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name) {
        Director director = service.getDirectorByName(name);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) {
        List<String> movies = service.getMoviesByDirectorName(director);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> movies = service.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director) {
        service.deleteDirectorByName(director);
        return new ResponseEntity<>(" success ", HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        service.deleteAllDirector();
        return new ResponseEntity<>(" success ", HttpStatus.OK);
    }
}