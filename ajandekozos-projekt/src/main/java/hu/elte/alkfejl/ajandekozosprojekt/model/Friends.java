package hu.elte.alkfejl.ajandekozosprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FRIENDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Friends extends BaseEntity {

    @Column(nullable = false, unique = true)
    private int valami;
    
    
//    @ManyToMany(targetEntity=UserLogin.class,fetch=FetchType.LAZY)
//    @JoinColumn(name="USER_ID")
//    private int user_id;
    
    
//    @ManyToMany(targetEntity=UserLogin.class,fetch=FetchType.LAZY)
//    @JoinColumn(name="USER2_ID")
//    private int user2_id;

}
