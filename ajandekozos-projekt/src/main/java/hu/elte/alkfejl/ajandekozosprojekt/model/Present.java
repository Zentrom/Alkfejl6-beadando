package hu.elte.alkfejl.ajandekozosprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRESENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Present extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String link;
    
    @Column(nullable = false)
    private char hidden;

    @ManyToOne(targetEntity=Wishlist.class,fetch=FetchType.LAZY)
    @JoinColumn(name="LIST_ID")
    private int list_id;
    
    @ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private int user_id;
    
}