package com.PlatMovie.Mapper;

import com.PlatMovie.controller.request.MovieRequest;
import com.PlatMovie.controller.response.CategoryResponse;
import com.PlatMovie.controller.response.MovieResponse;
import com.PlatMovie.controller.response.StreamingResponse;
import com.PlatMovie.entity.Category;
import com.PlatMovie.entity.Movie;
import com.PlatMovie.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest request){

        List<Category> categories = request.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = request.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();


        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .relaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie){

        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();


        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getRelaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();

    }
}
