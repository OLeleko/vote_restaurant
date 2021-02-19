package com.github.vote_restaurant.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name ="restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurant_name")})
public class Restaurant extends AbstractNamedEntity {

    public Restaurant() {

    }

    public Restaurant(Integer id, @NotBlank @Size(min = 2) String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
