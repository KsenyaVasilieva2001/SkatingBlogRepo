package ru.kpfu.itis.skatingblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.skatingblog.dto.UserDto;
import ru.kpfu.itis.skatingblog.exceptions.DuplicateEntryException;
import ru.kpfu.itis.skatingblog.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showRegistForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") @Valid UserDto userDto,
                               HttpServletRequest request,
                               BindingResult bindingResult, ModelMap map){

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try{
            userService.registerUser(userDto);
            return "redirect:/profile";
        }
        catch (DuplicateEntryException e){
            map.put("messageError", "Пользователь с таким email уже существует");
            return "registration";
        }
    }

}
