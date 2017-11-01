package hu.elte.alkfejl.ajandekozosprojekt.model;

import java.sql.Date;
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
@Table(name = "COMMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Date timestamp;
    
    @ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private int user_id;
    
    @ManyToOne(targetEntity=Present.class,fetch=FetchType.LAZY)
    @JoinColumn(name="PRESENT_ID")
    private int present_id;
}