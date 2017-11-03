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
    
    @ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "PRESENT_ID", referencedColumnName = "ID")
    private List<Comment> comments;
    
}