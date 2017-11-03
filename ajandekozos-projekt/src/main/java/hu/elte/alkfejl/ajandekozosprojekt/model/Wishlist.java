package hu.elte.alkfejl.ajandekozosprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WISHLIST")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WishList extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listId", cascade = CascadeType.ALL)
    private List<Present> presents;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
