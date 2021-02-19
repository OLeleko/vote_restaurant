package com.github.vote_restaurant.repository;

import com.github.vote_restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Menu m WHERE m.id=:id")
    Menu getById(@Param("id") Integer id);

    @Query("SELECT m FROM Menu m WHERE m.regist_date=:regist_date")
    List<Menu> getByDate(@Param("regist_date") LocalDate regist_date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);
}
