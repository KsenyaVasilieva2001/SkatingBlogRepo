package ru.kpfu.itis.skatingblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ru.kpfu.itis.skatingblog.dto.AuthDto;
import ru.kpfu.itis.skatingblog.dto.UserDto;
import ru.kpfu.itis.skatingblog.exceptions.DuplicateEntryException;
import ru.kpfu.itis.skatingblog.exceptions.WrongEmailOrPasswordException;
import ru.kpfu.itis.skatingblog.services.UserService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthController {


    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }




    @GetMapping("/signin")
    public String getLoginPage(){
        return "login";
    }

    @PermitAll
    @PostMapping(value = "/signin")
    public String signInUser(ModelMap map,  AuthDto loginDto) {
        try {
            userService.login(loginDto);
            map.put("Email", loginDto.getEmail());
            return "redirect:/profile";
        } catch (WrongEmailOrPasswordException e) {
            map.put("message", "Неправильный логин или пароль.");
        }
        return "redirect:/signin";

    }



}
