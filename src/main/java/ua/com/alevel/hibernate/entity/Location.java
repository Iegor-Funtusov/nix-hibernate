package ua.com.alevel.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 7:58 PM
 */

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location extends AbstractEntity {

    @NaturalId
    private String name;
}
