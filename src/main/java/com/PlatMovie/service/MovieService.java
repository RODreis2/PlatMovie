package com.PlatMovie.service;

import com.PlatMovie.entity.Category;
import com.PlatMovie.entity.Movie;
import com.PlatMovie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movierepository;

    public Movie save(Movie movie){
        return movierepository.save(movie);
    }

    public List<Movie> findAll(){
        return movierepository.findAll();
    }
    public Optional<Movie> getMovieId(long id){
        return movierepository.findById(id);
    }
    public void deleteMovieId(long id){
        movierepository.deleteById(id);
    }
    public void updateMovie(Movie movie) {
        movierepository.save(movie);
    }

}
