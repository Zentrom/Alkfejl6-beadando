package hu.elte.alkfejl.ajandekozosprojekt.api;

import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.WishListService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@RestController
@RequestMapping("/api/lists")
public class WishListApiController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private UserService userService;

    @Role({ADMIN, USER})
    @GetMapping
    private ResponseEntity<Iterable<WishList>> list() {
        Iterable<WishList> issues = wishListService.listByRole(userService.getUser());
        return ResponseEntity.ok(issues);
    }

    @Role({ADMIN, USER})
    @PostMapping
    private ResponseEntity<WishList> create(@RequestBody WishList list) {
        WishList saved = wishListService.create(list);
        return ResponseEntity.ok(saved);
    }

    @Role({ADMIN, USER})
    @PutMapping("/{id}")
    private ResponseEntity<WishList> update(@PathVariable int id, @RequestBody WishList wishList) {
        WishList updated = wishListService.update(id, wishList);
        return ResponseEntity.ok(updated);
    }

    @Role({ADMIN, USER})
    @GetMapping("/{id}")
    private ResponseEntity<WishList> read(@RequestParam int id) {
        WishList read = wishListService.read(id);
        return ResponseEntity.ok(read);
    }

    @Role(ADMIN)
    @DeleteMapping("/{id}")
    private ResponseEntity delete(@RequestParam int id) {
        wishListService.delete(id);
        return ResponseEntity.ok().build();
    }
}