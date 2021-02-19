package com.github.vote_restaurant.repository;

import com.github.vote_restaurant.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    Vote getById(@Param("id") Integer id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.vote_date=:vote_date AND v.user.id=:userId")
    Vote getByDate(@Param("vote_date") LocalDate vote_date, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.vote_date >=:start_date AND v.vote_date <=:end_date ORDER BY v.vote_date DESC")
    List<Vote> getBetween(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.vote_date DESC")
    List<Vote> getAll(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") Integer id, @Param("userId") int userId);
}
