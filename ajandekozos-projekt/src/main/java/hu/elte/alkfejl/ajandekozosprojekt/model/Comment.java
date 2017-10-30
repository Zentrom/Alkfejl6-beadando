package hu.elte.alkfejl.ajandekozosprojekt.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
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

}