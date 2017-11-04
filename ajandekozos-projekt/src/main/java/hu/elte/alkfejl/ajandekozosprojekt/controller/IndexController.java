package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/user/profile")
    public String profile(@ModelAttribute User user,Model model) {
        model.addAttribute("user", user);
        return "/user/profile";
    }
    
    @GetMapping("/user/friends")
    public String friends(@ModelAttribute User user,Model model) {
        model.addAttribute("user", user);
        return "/user/profile";
    }
    
    @GetMapping("/user/settings")
    public String settings(@ModelAttribute User user,Model model) {
        model.addAttribute("user", user);
        return "/user/profile";
    }
    
    @GetMapping("/user/wishlist")
    public String wishlist(@ModelAttribute User user,Model model) {
        model.addAttribute("user", user);
        return "/user/profile";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if (userService.isValid(user)) {
            return redirectToProfile(user);
            //return pathParameter(user.getUsername());
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.register(user);

        return redirectToProfile(user);
    }
    
    //@GetMapping("/user/profile")
    private String redirectToProfile(@ModelAttribute User user) {
        return "redirect:/user/profile?username=" + user.getUsername();
        //return "/user/profile";// + user.getUsername();
        //return "/user/"+ user.getUsername() +"/profile";
    }
    
    /*@RequestMapping("/{name}")
    public String pathParameter(@PathVariable String username) {
        return username; 
    }*/
    
//    private String redirectToProfile(int user_id) {
//        //return "redirect:/user/profile?name=" + user.getUsername();
//        return "/user/profile";
//    }
    
}