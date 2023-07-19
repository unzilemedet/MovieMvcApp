package com.bilgeadam.controller;

import com.bilgeadam.dto.request.UserRegisterRequestDto;
import com.bilgeadam.dto.response.UserLoginResponseDto;
import com.bilgeadam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //GetMapping, PostMapping
    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterRequestDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.registerMapper(dto);
            //modelAndView.addObject("email", dto.getEmail());
            modelAndView.setViewName("redirect:login");
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(String email) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email", email);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginResponseDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            UserLoginResponseDto responseDto = userService.loginMapper(dto);
            modelAndView.addObject("userId", responseDto.getId());
            modelAndView.setViewName("redirect:/movies/find-all");
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}
