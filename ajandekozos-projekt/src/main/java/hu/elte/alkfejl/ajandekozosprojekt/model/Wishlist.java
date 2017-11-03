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

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "LIST_ID", referencedColumnName = "ID")
    private List<Present> presents;
}
