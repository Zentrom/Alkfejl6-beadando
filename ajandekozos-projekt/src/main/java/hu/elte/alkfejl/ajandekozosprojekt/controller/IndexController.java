package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.model.UserData;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin.Role.USER;
import hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public String homePage() {
        return "redirect:index";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new UserLogin()); 
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userData", new UserData()); 
        return "register";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute UserLogin userLogin, Model model) {
        if (userService.isValid(userLogin)) {
            return redirectToProfile(userLogin);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute UserData userData) {
        userService.register(userData);

        return redirectToProfile(userData.getUserLogin());
    }
    
    private String redirectToProfile(@ModelAttribute UserLogin userLogin) {
        //return "redirect:/user/profile?name=" + userLogin.getUsername();
        return "/user/profile";
    }
    
//    private String redirectToProfile(int user_id) {
//        //return "redirect:/user/profile?name=" + userLogin.getUsername();
//        return "/user/profile";
//    }
    
}