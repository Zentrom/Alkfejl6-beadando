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

    public CommentDTO create(int presentId, User user, CommentDTO newCommentDTO) {
        Present present = presentRepository.findOne(presentId);

        Comment comment = new Comment();
        comment.setText(newCommentDTO.getText());
        comment.setUser(user);
        comment.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        comment.setPresent(present);
        Comment saved = commentRepository.save(comment);

        newCommentDTO.setId(saved.getId());
        newCommentDTO.setAuthor(new UserDTO(user.getId(), user.getFirstname(), user.getLastname()));
        newCommentDTO.setTimeStamp(saved.getTimestamp());
        return newCommentDTO;
    }

    public void delete(int commentId) {
        commentRepository.delete(commentId);
    }

}
