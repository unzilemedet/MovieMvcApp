package com.bilgeadam.controller;

import com.bilgeadam.entity.Movie;
import com.bilgeadam.entity.User;
import com.bilgeadam.exception.MovieExceptionHandler;
import com.bilgeadam.service.CommentService;
import com.bilgeadam.service.GenreService;
import com.bilgeadam.service.MovieService;
import com.bilgeadam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final UserService userService;
    private final GenreService genreService;
    private final CommentService commentService;


    @GetMapping("/find-all")
    public ModelAndView getMoviePage(Integer userId) {
        User user;
        ModelAndView modelAndView = new ModelAndView();
        if (userId == null) {
            modelAndView.addObject("user", null);
        } else {
            user = userService.findById(userId).get();
            modelAndView.addObject("user", user);
        }

        List<Movie> movies = movieService.findAll();
        modelAndView.addObject("movies", movies);
        modelAndView.addObject("genreservice", genreService);
        modelAndView.setViewName("movies");
        return modelAndView;
    }

    @GetMapping("/find-by-id/{id}")
    public ModelAndView getMovieById(@PathVariable Integer id, @RequestParam(required = false) Integer userId) {
        ModelAndView modelAndView = new ModelAndView();
        User user;
        if (userId == null) {
            modelAndView.addObject("user", null);
        } else {
            user = userService.findById(userId).get();
            modelAndView.addObject("user", user);
        }

        Optional<Movie> movie = movieService.findById(id);
        if (movie.isPresent()) {
            modelAndView.addObject("movie", movie.get());
            modelAndView.addObject("genreservice", genreService);
            modelAndView.addObject("commentservice", commentService);
            modelAndView.addObject("userservice", userService);
            modelAndView.setViewName("moviesDetail");
        } else {
            throw new RuntimeException("Hata");
        }
        return modelAndView;
    }
}
