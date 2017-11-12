package hu.elte.alkfejl.ajandekozosprojekt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<WishList> wishLists;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "FRIENDS", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "FRIEND_ID")})
    private List<User> friends;

    // TODO mind a két oldalt definiálni kell? Lehet elég lenne csak az egyik
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requestee", cascade = CascadeType.ALL)
    private Set<FriendRequest> friendRequestsFromUsers;

/*    @OneToMany(fetch = FetchType.LAZY, mappedBy = "REQUESTER_ID", cascade = CascadeType.ALL)
    private List<FriendRequest> usersToRequest;*/

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        GUEST, USER, ADMIN
    }
    
}