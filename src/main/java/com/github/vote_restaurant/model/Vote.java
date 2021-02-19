package com.github.vote_restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "user_id"}, name = "vote_idx")})
public class Vote extends AbstractBaseEntity {
    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate vote_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @NotNull
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    public Vote() {
    }

    public Vote(Integer id, @NotNull LocalDate vote_date) {
        super(id);
        this.vote_date = vote_date;
    }

    public LocalDate getVote_date() {
        return vote_date;
    }

    public void setVote_date(LocalDate vote_date) {
        this.vote_date = vote_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "vote_date=" + vote_date +
                ", user=" + user +
                ", menu=" + menu +
                ", id=" + id +
                '}';
    }
}
