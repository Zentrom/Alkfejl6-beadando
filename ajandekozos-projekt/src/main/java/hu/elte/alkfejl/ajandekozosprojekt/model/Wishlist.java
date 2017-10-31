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
@Table(name = "WISHLIST")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Wishlist extends BaseEntity {

    @Column(nullable = false)
    private String title;
    
    @ManyToOne(targetEntity=UserLogin.class,fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private int user_id;

}
