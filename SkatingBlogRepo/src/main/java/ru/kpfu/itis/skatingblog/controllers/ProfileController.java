package ru.kpfu.itis.skatingblog.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
public class ProfileController {


    @GetMapping("/profile")
    public String getProfile(){
        return "profile";
    }

}
