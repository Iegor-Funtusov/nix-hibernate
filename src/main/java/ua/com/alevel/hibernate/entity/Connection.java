package ua.com.alevel.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:01 PM
 */

@Getter
@Setter
@Entity
@Table(name = "connections")
public class Connection extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "from_id")
    private Location from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "to_id")
    private Location to;

    private Integer cost;
}
