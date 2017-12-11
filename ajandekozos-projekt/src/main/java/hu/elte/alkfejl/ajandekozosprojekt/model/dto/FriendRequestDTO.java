package hu.elte.alkfejl.ajandekozosprojekt.model.dto;

import hu.elte.alkfejl.ajandekozosprojekt.model.FriendRequest;
import lombok.Data;

@Data
public class FriendRequestDTO {

    private int id;
    private int requesteeId;
    private int requesterId;
    private String requesterName;
    private String requesteeName;

/*   private UserDTO requester;
    private UserDTO requestee;

    private FriendRequest.Status status = FriendRequest.Status.PENDING;*/

}
