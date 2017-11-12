package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.ResourceConstants;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import hu.elte.alkfejl.ajandekozosprojekt.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping(ResourceConstants.USER_LOGOUT)
    public ResponseEntity logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }

    @PostMapping(ResourceConstants.USER_REGISTER)
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.FRIENDS)
    public ResponseEntity<Iterable<User>> listFriends() {
        Iterable<User> friends = userService.listFriends();
        return ResponseEntity.ok(friends);
    }

    @Role(USER)
    @DeleteMapping(ResourceConstants.DELETE_FRIEND_OR_USER)
    public ResponseEntity deleteFriend(@PathVariable int friendId) {
        userService.deleteFriend(friendId);
        return ResponseEntity.ok().build();
    }

    // TODO jó így az endpoint?
    @Role(ADMIN)
    @DeleteMapping(ResourceConstants.DELETE_FRIEND_OR_USER)
    public ResponseEntity deleteUser(@PathVariable("friendId") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
