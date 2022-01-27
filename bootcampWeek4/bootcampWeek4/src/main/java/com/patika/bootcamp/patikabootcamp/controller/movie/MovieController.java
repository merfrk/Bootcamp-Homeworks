package com.patika.bootcamp.patikabootcamp.controller.movie;

import com.patika.bootcamp.patikabootcamp.service.actor.Actor;
import com.patika.bootcamp.patikabootcamp.service.movie.Movie;
import com.patika.bootcamp.patikabootcamp.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieCreateResponse create(@RequestBody @Valid MovieRequest request) {
        Movie movie = request.convertToMovie();
        List<Actor> actors = request.convertToActors();

        Long id = movieService.create(movie, actors, request.getActorIds());
        return MovieCreateResponse.convertToMovieResponse(id);
    }

    @GetMapping("/movies/{id}")
    public MovieResponse retrieve(@PathVariable Long id) {
        Movie movie = movieService.retrieve(id);
        return MovieResponse.convertFrom(movie);
    }

    @DeleteMapping("/movies/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long Id){
        movieService.delete(Id);
    }
}
