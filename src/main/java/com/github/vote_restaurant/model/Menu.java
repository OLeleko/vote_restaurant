package com.github.vote_restaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"regist_date", "restaurant_id"}, name = "menu_idx")})
public class Menu extends AbstractBaseEntity {

    @Column(name = "regist_date", nullable = false)
    @NotNull
    private LocalDate regist_date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @OrderBy("price DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Meal> meals;

    public Menu() {
    }

    public Menu(Integer id, @NotNull LocalDate regist_date) {
        super(id);
        this.regist_date = regist_date;
    }

    public LocalDate getRegist_date() {
        return regist_date;
    }

    public void setRegist_date(LocalDate regist_date) {
        this.regist_date = regist_date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "regist_date=" + regist_date +
                ", restaurant=" + restaurant +
                ", id=" + id +
                '}';
    }
}
