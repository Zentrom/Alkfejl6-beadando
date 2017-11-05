package hu.elte.alkfejl.ajandekozosprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRESENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Present extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column
    private String link;

    @Column(nullable = false)
    private boolean hidden;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(targetEntity = WishList.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "LIST_ID")
    private WishList wishList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "presentId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Name: " + name + " , price: " + price + ", link: " + link;
    }
}