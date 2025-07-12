package com.PlatMovie.controller;


import com.PlatMovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PlatMovie/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
}
