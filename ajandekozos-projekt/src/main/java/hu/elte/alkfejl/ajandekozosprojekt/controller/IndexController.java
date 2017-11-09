package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.WishListService;
import hu.elte.alkfejl.ajandekozosprojekt.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private WishListService wishListService;
    
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
            try {
                userService.login(user);
            } catch (UserNotValidException e) {
                System.err.println("Error: User was not found!");
            }
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
        return "redirect:/user/profile?username=" + user.getUsername();
    }
    
    @GetMapping("/user/profile")
    public String profile(@RequestParam(required = false, defaultValue = "world", name = "username") String name,Model model){//,@RequestParam(required = false, defaultValue = "world", name = "username") String name) {
        model.addAttribute("user", userService.getUser());
        return "/user/profile";
    }

    @GetMapping("/user/friends")
    public String friends(@RequestParam(required = false, defaultValue = "world", name = "username") String name,Model model) {
/*        User tmpUser = userService.findByUserName(name);*/
        User tmpUser = userService.getUser();
        model.addAttribute("user", tmpUser);
        model.addAttribute("friends", tmpUser.getFriends());
        return "/user/friends";
    }
    
    @GetMapping("/user/wishlist")
    public String wishlist(@RequestParam(required = false, defaultValue = "world", name = "username") String name, @RequestParam(required = false, defaultValue = "world", name = "friendname") String friendname, Model model) {
/*        if(friendname.equals("null")){
            User tmpUser = userService.getUserRepository().findByUsername(name).get();
            model.addAttribute("user", tmpUser);
            model.addAttribute("wishlists", tmpUser.getWishLists());
            model.addAttribute("friendname", friendname);
            return "/user/wishlist";
        }else{
            User tmpUser = userService.getUserRepository().findByUsername(name).get();
            User friendUser = userService.getUserRepository().findByUsername(friendname).get();
            model.addAttribute("user", tmpUser);
            model.addAttribute("wishlists", friendUser.getWishLists());
            model.addAttribute("friendname", friendname);
            return "/user/wishlist";
        }*/

        User tmpUser = userService.findByUserName(name);
        model.addAttribute("user", tmpUser);
        model.addAttribute("friendname", friendname);

        System.out.println("FRIEND NAME: " + friendname);
        if (friendname.equals("null")) {
            model.addAttribute("wishlists", tmpUser.getWishLists());
        } else {
            System.out.println("BENT");
            User friendUser = userService.findByUserName(friendname);
            model.addAttribute("wishlists", friendUser.getWishLists());
        }

        return "/user/wishlist";
    }

    @GetMapping("/user/presents")
    public String presents(@RequestParam(required = false, defaultValue = "world", name = "username") String name,@RequestParam(required = false, defaultValue = "world", name = "wishlist") String wish,@RequestParam(required = false, defaultValue = "world", name = "friendname") String friendname,Model model) {
        User tmpUser = userService.getUser();
        WishList wishList = wishListService.findByTitle(wish);
        model.addAttribute("user", tmpUser);
        //model.addAttribute("wishlists", tmpWishlist);
        model.addAttribute("friendname", friendname);
        model.addAttribute("presents", wishList.getPresents());
        return "/user/presents";
    }

    @GetMapping("/user/settings")
    public String settings(@RequestParam(required = false, defaultValue = "world", name = "username") String name, Model model) {
        model.addAttribute("user", userService.getUser());
        System.out.println("GET USER ID: " + userService.getUser().getId());
        System.out.println("BENT GET");
        return "/user/settings";
    }

    @PostMapping("/user/settings")
    public String settings(@ModelAttribute User user, Model model) {
        user.setId(userService.getUser().getId());
        System.out.println("POST USER ID: " + user.getId());
        System.out.println("BENT POST");
        userService.update(user.getId(), user);
        model.addAttribute("updated", true);
        return "user/settings";
    }


}