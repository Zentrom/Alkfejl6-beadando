package hu.elte.alkfejl.ajandekozosprojekt.model.dto;

import lombok.Data;

@Data
public class FriendRequestDTO {

    private int id;
    private int requesteeId;
    private int requesterId;
    private String requesterName;
    private String requesteeName;

}
