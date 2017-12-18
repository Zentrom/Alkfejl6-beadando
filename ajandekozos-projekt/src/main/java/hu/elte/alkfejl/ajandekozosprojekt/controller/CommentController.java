package hu.elte.alkfejl.ajandekozosprojekt.controller;

import hu.elte.alkfejl.ajandekozosprojekt.ResourceConstants;
import hu.elte.alkfejl.ajandekozosprojekt.model.Comment;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.CommentDTO;
import hu.elte.alkfejl.ajandekozosprojekt.service.CommentService;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@RestController
@RequestMapping()
public class CommentController {

    private CommentService commentService;

    private UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @Role({ADMIN, USER})
    @GetMapping(ResourceConstants.COMMENTS)
    public ResponseEntity<List<CommentDTO>> listComments(@PathVariable int friendPresentId) {
        List<CommentDTO> comments = commentService.findAllByPresentId(friendPresentId);
        return ResponseEntity.ok(comments);
    }

    @Role(USER)
    @PostMapping(ResourceConstants.COMMENTS)
    public ResponseEntity<CommentDTO> addComment(@PathVariable int friendPresentId, @RequestBody CommentDTO comment) {
        CommentDTO saved = commentService.create(friendPresentId, userService.getUser(), comment);
        return ResponseEntity.ok(saved);
    }

    @Role(ADMIN)
    @DeleteMapping(ResourceConstants.COMMENTID)
    public ResponseEntity deleteComment(@PathVariable int commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }

}

