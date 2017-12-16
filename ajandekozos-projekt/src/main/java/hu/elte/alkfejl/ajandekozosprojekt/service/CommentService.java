package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.Comment;
import hu.elte.alkfejl.ajandekozosprojekt.model.Present;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.CommentDTO;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.UserDTO;
import hu.elte.alkfejl.ajandekozosprojekt.repository.CommentRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
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

    public List<CommentDTO> findAllByPresentId(int presentId) {
        List<CommentDTO> commentsDTO = new LinkedList<>();
        List<Comment> comments = commentRepository.findAllByPresentId(presentId);

        for (Comment comment: comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setText(comment.getText());
            commentDTO.setTimeStamp(comment.getTimestamp());
            commentDTO.setAuthor(new UserDTO(comment.getUser().getId(), comment.getUser().getFirstname(), comment.getUser().getLastname()));

            commentsDTO.add(commentDTO);
        }

        return commentsDTO;
    }

    // TODO valószínűleg nem kell
    public CommentDTO findById(int commentId) {
        CommentDTO foundDTO = new CommentDTO();
        Comment found = commentRepository.findOne(commentId);

        foundDTO.setId(found.getId());
        foundDTO.setText(found.getText());
        foundDTO.setTimeStamp(found.getTimestamp());
        foundDTO.setAuthor(new UserDTO(found.getUser().getId(), found.getUser().getFirstname(), found.getUser().getLastname()));

        return foundDTO;
    }

    public boolean checkPermission(User user, int commentId) {
        Comment comment = commentRepository.findOne(commentId);
        if (comment.getUser().equals(user) || user.getRole().equals(User.Role.ADMIN)) {
            return true;
        }

        return false;
    }

    public CommentDTO create(int presentId, User user, Comment comment) {
        CommentDTO savedDTO = new CommentDTO();

        Present present = presentRepository.findOne(presentId);
        // TODO jó a dátum így?
        comment.setUser(user);
        comment.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        comment.setPresent(present);
        Comment saved = commentRepository.save(comment);

        savedDTO.setId(saved.getId());
        savedDTO.setText(saved.getText());
        savedDTO.setTimeStamp(saved.getTimestamp());
        savedDTO.setAuthor(new UserDTO(saved.getUser().getId(), saved.getUser().getFirstname(), saved.getUser().getLastname()));

        return savedDTO;
    }

    public CommentDTO update(int commentId, Comment comment) {
        CommentDTO updatedDTO = new CommentDTO();
        Comment currentComment = commentRepository.findOne(commentId);

        currentComment.setText(comment.getText());
        currentComment.setTimestamp(comment.getTimestamp());
        // TODO settelni kell az usert is? -> valószínűleg nem kell
/*        currentComment.setUser(comment.getUser());*/
        Comment updated = commentRepository.save(comment);
        updatedDTO.setText(updated.getText());
        updatedDTO.setTimeStamp(updated.getTimestamp());

        return updatedDTO;
    }

    public void delete(int commentId) {
        commentRepository.delete(commentId);
    }

}
