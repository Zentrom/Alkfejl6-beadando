package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.ResourceConstants;
import hu.elte.alkfejl.ajandekozosprojekt.model.Present;
import hu.elte.alkfejl.ajandekozosprojekt.service.PresentService;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@RestController
public class PresentController {

    private PresentService presentService;

    private UserService userService;

    @Autowired
    public PresentController(PresentService presentService, UserService userService) {
        this.presentService = presentService;
        this.userService = userService;
    }

    @Role(USER)
    @GetMapping(ResourceConstants.PRESENTS)
    public ResponseEntity<Iterable<Present>> list(@PathVariable int wishlistId) {
        Iterable<Present> presents = presentService.findAllByListIdForOwner(wishlistId);
        return ResponseEntity.ok(presents);
    }

    @Role(USER)
    @PostMapping(ResourceConstants.PRESENTS)
    public ResponseEntity<Present> addPresent(@PathVariable int wishlistId, @RequestBody Present present) {
        Present saved = presentService.create(wishlistId, present, userService.getUser());
        return ResponseEntity.ok(saved);
    }

    @Role(USER)
    @DeleteMapping(ResourceConstants.PRESENTSID)
    public ResponseEntity deletePresent(@PathVariable int presentId) {
        presentService.delete(presentId);
        return ResponseEntity.ok().build();
    }

    @Role(USER)
    @PatchMapping(ResourceConstants.PRESENTSID)
    public ResponseEntity<Present> updatePresent(@PathVariable int presentId, @RequestBody Present present) {
        Present updated = presentService.updateByListOwnerOrAdmin(presentId, present);
        return ResponseEntity.ok(updated);
    }

    // TODO kell ez?
    @Role(USER)
    @GetMapping(ResourceConstants.PRESENTSID)
    public ResponseEntity<Present> readPresent(@PathVariable int presentId) {
        Present read = presentService.findById(presentId);
        return ResponseEntity.ok(read);
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.FRIEND_PRESENTS)
    public ResponseEntity<Iterable<Present>> listPresentsOfFriendsOrUsersList(@PathVariable int friendListId) {
        Iterable<Present> presentOfFriendList = presentService.findAllByListId(friendListId);
        return ResponseEntity.ok(presentOfFriendList);
    }

    @Role({ADMIN, USER})
    @PostMapping(ResourceConstants.FRIEND_PRESENTS)
    public ResponseEntity<Present> addNewPresentForFriendOrUser(@PathVariable int wishListId, @RequestBody Present present) {
        Present saved = presentService.create(wishListId, present, userService.getUser());
        return ResponseEntity.ok(saved);
    }

    // TODO kell ez?
    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.FRIEND_PRESENTID)
    public ResponseEntity<Present> readFriendPresent(@PathVariable int friendPresentId) {
        Present read = presentService.findById(friendPresentId);
        return ResponseEntity.ok(read);
    }

    // TODO jó az hogy a Role-t a presentService.updatePresent-en belül nézzük?
    @Role({ADMIN, USER})
    @PatchMapping(ResourceConstants.FRIEND_PRESENTID)
    public ResponseEntity<Present> updateFriendOrUserPresent(@PathVariable("friendPresentId") int presentId, @RequestBody Present present) {
        Present updated = presentService.updatePresent(userService.getUser(), presentId, present);
        return ResponseEntity.ok(updated);
    }

    @Role(ADMIN)
    @DeleteMapping(ResourceConstants.FRIEND_PRESENTID)
    public ResponseEntity deleteUserPresent(@PathVariable("friendPresentId") int presentId) {
        presentService.delete(presentId);
        return ResponseEntity.ok().build();
    }


}
