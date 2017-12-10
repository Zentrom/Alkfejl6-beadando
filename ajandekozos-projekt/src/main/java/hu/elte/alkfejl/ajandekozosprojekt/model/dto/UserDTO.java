package hu.elte.alkfejl.ajandekozosprojekt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String firstname;
    private String lastname;

}
