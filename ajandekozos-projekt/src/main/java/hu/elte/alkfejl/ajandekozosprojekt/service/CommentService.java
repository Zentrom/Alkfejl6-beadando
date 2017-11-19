package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.Comment;
import hu.elte.alkfejl.ajandekozosprojekt.model.Present;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.repository.CommentRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
@SessionScope
public class CommentService {

    private CommentRepository commentRepository;

    private PresentRepository presentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PresentRepository presentRepository) {
        this.commentRepository = commentRepository;
        this.presentRepository = presentRepository;
    }

    public List<Comment> findAllByPresentId(int presentId) {
        return commentRepository.findAllByPresentId(presentId);
    }

    public Comment findById(int commentId) {
        return commentRepository.findOne(commentId);
    }

    public boolean checkPermission(User user, int commentId) {
        Comment comment = commentRepository.findOne(commentId);
        if (comment.getUser().equals(user) || user.getRole().equals(User.Role.ADMIN)) {
            return true;
        }

        return false;
    }

    public Comment create(int presentId, User user, Comment comment) {
        Present present = presentRepository.findOne(presentId);
        // TODO jó a dátum így?
        comment.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        comment.setPresent(present);

        return commentRepository.save(comment);
    }

    public Comment update(int commentId, Comment comment) {
        Comment currentComment = commentRepository.findOne(commentId);
        currentComment.setText(comment.getText());
        currentComment.setTimestamp(comment.getTimestamp());
        // TODO settelni kell az usert is? -> valószínűleg nem kell
/*        currentComment.setUser(comment.getUser());*/

        return commentRepository.save(comment);
    }

    public void delete(int commentId) {
        commentRepository.delete(commentId);
    }

}
