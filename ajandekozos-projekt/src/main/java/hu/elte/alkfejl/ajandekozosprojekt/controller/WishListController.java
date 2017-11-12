package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.ResourceConstants;
import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.WishListService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;

@RestController
public class WishListController {

    private UserService userService;

    private WishListService wishListService;

    @Autowired
    public WishListController(UserService userService, WishListService wishListService) {
        this.userService = userService;
        this.wishListService = wishListService;
    }

    @GetMapping(ResourceConstants.WISHLISTS)
    public ResponseEntity<Iterable<WishList>> list() {
        Iterable<WishList> wishLists = wishListService.listByRole(userService.getUser());
        return ResponseEntity.ok(wishLists);
    }

    @PostMapping(ResourceConstants.WISHLISTS)
    public ResponseEntity<WishList> addWishList(@RequestBody WishList wishList) {
        WishList saved = wishListService.create(wishList);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping(ResourceConstants.WISHLISTSID)
    public ResponseEntity deleteWishList(@PathVariable int wishlistId) {
        wishListService.delete(wishlistId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(ResourceConstants.WISHLISTSID)
    public ResponseEntity<WishList> updateWishList(@PathVariable int wishlistId, @RequestBody WishList wishList) {
        WishList updated = wishListService.update(wishlistId, wishList);
        return ResponseEntity.ok(updated);
    }

    @GetMapping(ResourceConstants.WISHLISTSID)
    public ResponseEntity<WishList> readWishList(@PathVariable int wishlistId) {
        WishList read = wishListService.findById(wishlistId);
        return ResponseEntity.ok(read);
    }

    @GetMapping(ResourceConstants.FRIEND_LISTS)
    public ResponseEntity<Iterable<WishList>> listFriendsLists(@PathVariable int friendId) {
        Iterable<WishList> friendsLists = wishListService.findAllByUserId(friendId);
        return ResponseEntity.ok(friendsLists);
    }

    @Role(ADMIN)
    @DeleteMapping(ResourceConstants.DELETE_USER_LIST)
    public ResponseEntity deleteFriendsList(@PathVariable int userListId) {
        wishListService.delete(userListId);
        return ResponseEntity.ok().build();
    }

}
