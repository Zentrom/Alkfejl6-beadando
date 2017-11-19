package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.ResourceConstants;
import hu.elte.alkfejl.ajandekozosprojekt.model.Comment;
import hu.elte.alkfejl.ajandekozosprojekt.service.CommentService;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@RestController
@RequestMapping(ResourceConstants.COMMENTS)
public class CommentController {

    private CommentService commentService;

    private UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @Role({ADMIN, USER})
    @GetMapping("")
    public ResponseEntity<Iterable<Comment>> listComments(@PathVariable int friendPresentId) {
        Iterable<Comment> comments = commentService.findAllByPresentId(friendPresentId);
        return ResponseEntity.ok(comments);
    }

    @Role(USER)
    @PostMapping("")
    public ResponseEntity<Comment> addComment(@PathVariable int friendPresentId, @RequestBody Comment comment) {
        Comment saved = commentService.create(friendPresentId, userService.getUser(), comment);
        return ResponseEntity.ok(saved);
    }

    @Role({ADMIN, USER})
    @DeleteMapping(ResourceConstants.COMMENTID)
    public ResponseEntity deleteComment(@PathVariable int commentId) {
        if (commentService.checkPermission(userService.getUser(), commentId)) {
            commentService.delete(commentId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // TODO ha nem jó a permission akkor most milyen commentes is kap paraméterben? -> kliensoldali varázslat
    @Role({ADMIN, USER})
    @PatchMapping(ResourceConstants.COMMENTID)
    public ResponseEntity<Comment> updateComment(@PathVariable int commentId, @RequestBody Comment comment) {
        if (commentService.checkPermission(userService.getUser(), commentId)) {
            Comment updated = commentService.update(commentId, comment);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.badRequest().build();
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.COMMENTID)
    public ResponseEntity<Comment> readComment(@PathVariable int commentId) {
        Comment read = commentService.findById(commentId);
        return ResponseEntity.ok(read);
    }

}
