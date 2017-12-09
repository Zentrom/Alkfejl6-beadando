package hu.elte.alkfejl.ajandekozosprojekt.model.dto;

import lombok.Data;

@Data
public class PresentDTO {

    private int id;
    private String name;
    private int price;
    private String link;
    private boolean hidden;
    private UserDTO user;

}
