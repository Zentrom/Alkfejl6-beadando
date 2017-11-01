package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if (userService.isValid(user)) {
            return redirectToProfile(user);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.register(user);

        return redirectToProfile(user);
    }
    
    private String redirectToProfile(@ModelAttribute User user) {
        //return "redirect:/user/profile?name=" + user.getUsername();
        return "/user/profile";
    }
    
//    private String redirectToProfile(int user_id) {
//        //return "redirect:/user/profile?name=" + user.getUsername();
//        return "/user/profile";
//    }
    
}