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

    @GetMapping(ResourceConstants.USER)
    public ResponseEntity getUser() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping(ResourceConstants.USER_LOGIN)
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping(ResourceConstants.USER_REGISTER)
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.register(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.USER_LOGOUT)
    public ResponseEntity logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.FRIENDS)
    public ResponseEntity<List<UserDTO>> listFriends(@RequestParam(name = "firstname") String firstName, @RequestParam(name = "lastname") String lastName) {
        List<UserDTO> friends = userService.listFriends(firstName,lastName);
        return ResponseEntity.ok(friends);
    }

    @Role({ADMIN, USER})
    @DeleteMapping(ResourceConstants.DELETE_FRIEND)
    public ResponseEntity deleteFriend(@PathVariable int friendId) {
        userService.deleteFriend(friendId);
        return ResponseEntity.ok().build();
    }

    @Role(ADMIN)
    @GetMapping(ResourceConstants.USERS)
    public ResponseEntity<List<UserDTO>> listUsers(@RequestParam(name = "firstname") String firstName, @RequestParam(name = "lastname") String lastName) {
        List<UserDTO> users = userService.listUsers(firstName, lastName);
        return ResponseEntity.ok(users);
    }

    @Role(ADMIN)
    @DeleteMapping(ResourceConstants.USERSID)
    public ResponseEntity deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }


}
