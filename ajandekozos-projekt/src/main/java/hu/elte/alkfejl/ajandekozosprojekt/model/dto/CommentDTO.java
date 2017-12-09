package hu.elte.alkfejl.ajandekozosprojekt.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private int id;
    private String text;
    private Date timeStamp;
    private String authorName;

}
