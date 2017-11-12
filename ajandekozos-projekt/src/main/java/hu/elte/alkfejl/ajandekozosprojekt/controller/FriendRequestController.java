package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.ResourceConstants;
import hu.elte.alkfejl.ajandekozosprojekt.model.FriendRequest;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.service.FriendRequestService;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendRequestController {

    private FriendRequestService friendRequestService;

    private UserService userService;

    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService, UserService userService) {
        this.friendRequestService = friendRequestService;
        this.userService = userService;
    }

    @GetMapping(ResourceConstants.FRIEND_REQUESTS)
    public ResponseEntity<Iterable<FriendRequest>> listRequests() {
        Iterable<FriendRequest> friendRequests = friendRequestService.findAllByRequesteeId(userService.getUser().getId());
        return ResponseEntity.ok(friendRequests);
    }

    @DeleteMapping(ResourceConstants.FRIEND_REQUESTID)
    public ResponseEntity processRequest(@PathVariable int friendRequestId, @RequestParam int status) {
        friendRequestService.process(friendRequestId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping(ResourceConstants.FRIEND_REQUESTID)
    public ResponseEntity<FriendRequest> findRequest(@PathVariable int friendRequestId) {
        FriendRequest found = friendRequestService.findById(friendRequestId);
        return ResponseEntity.ok(found);
    }

    @GetMapping(ResourceConstants.USER_SEARCH)
    public ResponseEntity<List<User>> searchUsers(@RequestParam(name = "firstname") String firstName, @RequestParam(name = "lastname") String lastName) {
        List<User> searchedUsers = userService.findPossibleFriends(firstName, lastName);
        System.out.println("USER KERESES");
        return ResponseEntity.ok(searchedUsers);
    }

    @PostMapping(ResourceConstants.CREATE_FRIEND_REQUEST)
    public ResponseEntity<FriendRequest> createFriendRequest(@PathVariable int requesteeId) {
        FriendRequest saved = friendRequestService.create(requesteeId, userService.getUser());
        return ResponseEntity.ok(saved);
    }

}
