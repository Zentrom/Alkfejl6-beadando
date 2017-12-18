package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.ResourceConstants;
import hu.elte.alkfejl.ajandekozosprojekt.model.Present;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.PresentDTO;
import hu.elte.alkfejl.ajandekozosprojekt.service.PresentService;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@RestController
public class PresentController {

    private PresentService presentService;

    @Autowired
    public PresentController(PresentService presentService) {
        this.presentService = presentService;
    }

    @Role(USER)
    @GetMapping(ResourceConstants.PRESENTS)
    public ResponseEntity<List<PresentDTO>> list(@PathVariable int wishlistId) {
        List<PresentDTO> presents = presentService.findAllByListIdForOwner(wishlistId);
        return ResponseEntity.ok(presents);
    }

    @Role({ADMIN, USER})
    @PostMapping(ResourceConstants.PRESENTS)
    public ResponseEntity<PresentDTO> addPresent(@PathVariable int wishlistId, @RequestBody PresentDTO present) {
        PresentDTO saved = presentService.create(wishlistId, present);
        return ResponseEntity.ok(saved);
    }

    @Role({ADMIN, USER})
    @DeleteMapping(ResourceConstants.PRESENTSID)
    public ResponseEntity deletePresent(@PathVariable int presentId) {
        presentService.delete(presentId);
        return ResponseEntity.ok().build();
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.FRIEND_PRESENTS)
    public ResponseEntity<List<PresentDTO>> listPresentsOfFriendsOrUsersList(@PathVariable int friendListId) {
        List<PresentDTO> presentOfFriendList = presentService.findAllByListId(friendListId);
        return ResponseEntity.ok(presentOfFriendList);
    }

    @Role({ADMIN, USER})
    @PatchMapping(ResourceConstants.FRIEND_PRESENTID)
    public ResponseEntity<PresentDTO> updateFriendPresent(@PathVariable("friendPresentId") int presentId, @RequestBody PresentDTO present) {
        PresentDTO updated = presentService.updateByFriend(presentId, present);
        return ResponseEntity.ok(updated);
    }

    @Role({ADMIN, USER})
    @PatchMapping(ResourceConstants.PRESENTSID)
    public ResponseEntity<PresentDTO> updatePresent(@PathVariable int presentId, @RequestBody PresentDTO present) {
        PresentDTO updated = presentService.updateByListOwnerOrAdmin(presentId, present);
        return ResponseEntity.ok(updated);
    }


}
