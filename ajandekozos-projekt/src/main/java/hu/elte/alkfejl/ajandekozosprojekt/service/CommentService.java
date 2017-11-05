package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.Comment;
import hu.elte.alkfejl.ajandekozosprojekt.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllByPresentId(int presentId) {
        return commentRepository.findAllByPresentId(presentId);
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment update(int id, Comment comment) {
        Comment currentComment = commentRepository.findOne(id);
        currentComment.setText(comment.getText());
        currentComment.setTimestamp(comment.getTimestamp());
        currentComment.setUser(comment.getUser());

        return commentRepository.save(comment);
    }

    public Comment read(int id) {
        return commentRepository.findOne(id);
    }

    public void delete(int id) {
        commentRepository.delete(id);
    }

}
