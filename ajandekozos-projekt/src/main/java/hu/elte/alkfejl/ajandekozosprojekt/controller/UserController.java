package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.ResourceConstants;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.UserDTO;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import hu.elte.alkfejl.ajandekozosprojekt.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO kellenek ide a Role-ok?
    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.USER)
    public ResponseEntity<User> user() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(ResourceConstants.USER_LOGIN)
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
    }
    }

    @PostMapping(ResourceConstants.USER_REGISTER)
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.USER_LOGOUT)
    public ResponseEntity logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.FRIENDS)
    public ResponseEntity<List<UserDTO>> listFriends() {
        List<UserDTO> friends = userService.listFriends();
        return ResponseEntity.ok(friends);
    }

    @Role({ADMIN, USER})
    @DeleteMapping(ResourceConstants.DELETE_FRIEND_OR_USER)
    public ResponseEntity deleteFriendOrUser(@PathVariable int friendId) {
        userService.deleteFriendOrUser(friendId);
        return ResponseEntity.ok().build();
    }
}
