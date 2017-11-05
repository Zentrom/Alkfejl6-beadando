package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    
    /*@GetMapping("{name}")
    public @ResponseBody String usiname(@PathVariable String name) {

       User user = new User();

       user.setUsername(name);
       user.setId(1);
       return "mama";
    }*/
    
   /* @GetMapping("/user/profile")
    public String profile(@ModelAttribute User user,Model model){//,@RequestParam(required = false, defaultValue = "world", name = "username") String name) {
        if(userService.isLoggedIn()){
            //model.addAttribute("user", userService.getUser());
            model.addAttribute("user", user);
            return "/user/profile";
        }
        return "index";
        //return "/user/profile?username=" + name;
        
    }*/
    
    @GetMapping("/user/profile")
    public String profile(@RequestParam(required = false, defaultValue = "world", name = "username") String name,Model model){//,@RequestParam(required = false, defaultValue = "world", name = "username") String name) {
        //if(userService.isLoggedIn()){
            //model.addAttribute("user", userService.getUser());
            model.addAttribute("user", userService.getUserRepository().findByUsername(name).get());
            return "/user/profile";
        //}
        //return "index";
        //return "/user/profile?username=" + name;
    }
    
    @GetMapping("/user/friends")
    public String friends(@RequestParam(required = false, defaultValue = "world", name = "username") String name,Model model) {
        User tmpUser = userService.getUserRepository().findByUsername(name).get();
        model.addAttribute("user", tmpUser);
        model.addAttribute("friends", tmpUser.getFriends());
        return "/user/friends";
    }
    
    @GetMapping("/user/settings")
    public String settings(@RequestParam(required = false, defaultValue = "world", name = "username") String name,Model model) {
        model.addAttribute("user", userService.getUserRepository().findByUsername(name).get());
        return "/user/settings";
    }
    
    @GetMapping("/user/wishlist")
    public String wishlist(@RequestParam(required = false, defaultValue = "world", name = "username") String name,Model model) {
        User tmpUser = userService.getUserRepository().findByUsername(name).get();
        model.addAttribute("user", tmpUser);
        model.addAttribute("wishlists", tmpUser.getWishLists());
        return "/user/wishlist";
    }
    
    @GetMapping("/user/presents")
    public String presents(@RequestParam(required = false, defaultValue = "world", name = "username") String name,@RequestParam(required = false, defaultValue = "world", name = "wishlist") String wish,Model model) {
        User tmpUser = userService.getUserRepository().findByUsername(name).get();
        List<WishList> tmpWishlist = tmpUser.getWishLists();
        model.addAttribute("user", tmpUser);
        //model.addAttribute("wishlists", tmpWishlist);
        int wishListid = 0;
        for(int i=0;i<tmpWishlist.size();i++){
            if(tmpWishlist.get(i).getTitle().equals(wish)){
                wishListid=i;
            }
        }
        model.addAttribute("presents", tmpWishlist.get(wishListid).getPresents());
        return "/user/presents";
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
    
    @PostMapping("/user/settings")
    public String settings(@ModelAttribute User user, Model model) {
        userService.update(user.getId(), user);
                
        return redirectToProfile(user);
    }
    
//    @PostMapping("/user/profile")
//    public String profilel(@ModelAttribute User user,Model model){//,@RequestParam(required = false, defaultValue = "world", name = "username") String name) {
//       // if(userService.isLoggedIn()){
//            //model.addAttribute("user", userService.getUser());
//            model.addAttribute("user", user);
//            return "/user/profile";
//       // }
//       // return "index";
//        //return "/user/profile?username=" + name;
//        
//    }
    
    //@GetMapping("/user/profile")
    private String redirectToProfile(@ModelAttribute User user) {
        return "redirect:/user/profile?username=" + user.getUsername();
        //return "/user/profile";// + user.getUsername();
        //return "/user/"+ user.getUsername() +"/profile";
        //return "/user/profile?username=" + user.getUsername();
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