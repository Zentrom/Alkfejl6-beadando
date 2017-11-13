package hu.elte.alkfejl.ajandekozosprojekt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDREQUEST")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FriendRequest extends BaseEntity {

    // TODO kell ide az ignore? Lazy vs Eager
    
    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID")
    private User requester;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTEE_ID")
    private User requestee;

    private Status status = Status.PENDING;

    public enum Status {
        PENDING, ACCEPTED, DECLINED;
    }

}
