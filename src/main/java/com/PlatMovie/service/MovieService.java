package com.PlatMovie.service;

import com.PlatMovie.Config.JWTuserData;
import com.PlatMovie.Config.SecurityFilter;
import com.PlatMovie.entity.Category;
import com.PlatMovie.entity.Movie;
import com.PlatMovie.entity.Streaming;
import com.PlatMovie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movierepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movierepository.save(movie);
    }

    public List<Movie> findAll(){
        return movierepository.findTop5ByOrderByRatingDesc();
    }

    public List<Movie> findByCategory(Long categoryId){
        return movierepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Optional<Movie> findById(Long id){
        return movierepository.findById(id);
    }
    public Optional<Movie> update(Long movieId, Movie updateMovie){
        Optional<Movie> optMovie = movierepository.findById(movieId);
        if (optMovie.isPresent()){
            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setReleaseDate(updateMovie.getReleaseDate());
            movie.setRating(updateMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movierepository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    public void delete(Long movieId){
        movierepository.deleteById(movieId);
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoryFound = new ArrayList<>();
        categories.forEach(category -> categoryService.findById(category.getId()).ifPresent(categoryFound::add));
        return categoryFound;
    }
    private List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.findStreamingById(streaming.getId()).ifPresent(streamingFound::add));
        return streamingFound;
    }
}