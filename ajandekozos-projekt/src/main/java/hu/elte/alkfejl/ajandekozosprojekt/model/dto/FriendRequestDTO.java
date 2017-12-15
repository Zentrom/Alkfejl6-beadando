package hu.elte.alkfejl.ajandekozosprojekt.model.dto;

import hu.elte.alkfejl.ajandekozosprojekt.model.FriendRequest;
import lombok.Data;

@Data
public class FriendRequestDTO {

    private int id;
    private UserDTO requester;
    private UserDTO requestee;

}
