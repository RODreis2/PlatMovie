package com.PlatMovie.controller;

import com.PlatMovie.Mapper.MovieMapper;
import com.PlatMovie.controller.request.MovieRequest;
import com.PlatMovie.controller.response.MovieResponse;
import com.PlatMovie.entity.Movie;
import com.PlatMovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PlatMovie/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request){
        Movie savedMovie = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieByid(long id){
        return movieService.getMovieId(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable long id) {
        return movieService.getMovieId(id)
                .map(movie  -> {
                    movieService.deleteMovieId(id);
                    return ResponseEntity.ok("O ID " + movie.getId() + "  foi deletado com sucesso.");
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovieById(@PathVariable long id, @RequestBody MovieRequest request) {
        return movieService.getMovieId(id)
                .map(existingMovie -> {
                    Movie updatedMovie = MovieMapper.toMovie(request);
                    updatedMovie.setId(existingMovie.getId());

                    movieService.updateMovie(updatedMovie);

                    return ResponseEntity.ok("Filme com ID " + id + " atualizado com sucesso.");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
