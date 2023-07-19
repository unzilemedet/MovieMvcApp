package com.bilgeadam.controller;

import com.bilgeadam.dto.request.MovieCommentCreateRequestDto;
import com.bilgeadam.entity.Comment;
import com.bilgeadam.entity.Movie;
import com.bilgeadam.entity.User;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.service.CommentService;
import com.bilgeadam.service.MovieService;
import com.bilgeadam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequiredArgsConstructor
@RequestMapping("/moviecomment")
public class MovieCommentController {

    private final CommentService movieCommentService;

    private final UserService userService;
    private  final MovieService movieService;

    @PostMapping("/save")
    public ModelAndView save(MovieCommentCreateRequestDto dto){
        Comment movieComment=movieCommentService.save(IUserMapper.INSTANCE.toMovieComment(dto));

        Movie movie=movieService.findById(dto.getMovieId()).get();
        movie.getCommentId().add(movieComment.getId());
        movieService.save(movie);
        User user=userService.findById(dto.getUserId()).get();
        user.getCommentId().add(movieComment.getId());
        userService.save(user);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/movies/find-by-id/"+dto.getMovieId()+"?userId="+dto.getUserId());
        return  modelAndView;
    }

}
