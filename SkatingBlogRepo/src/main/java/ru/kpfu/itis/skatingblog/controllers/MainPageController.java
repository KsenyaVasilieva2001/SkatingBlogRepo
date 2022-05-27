package ru.kpfu.itis.skatingblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.PermitAll;

@Controller
public class MainPageController {

    @PermitAll
    @GetMapping("/")

    public String getMainPage(Model model){
        return "main_page";
    }

}
