package com.github.vote_restaurant.TestData;

import com.github.vote_restaurant.TestMatcher;
import com.github.vote_restaurant.model.Vote;

import java.time.LocalDate;

import static com.github.vote_restaurant.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Vote.class, "user", "menu");
    public static final int VOTE_ID = START_SEQ + 44;

    public static LocalDate dateVote = LocalDate.parse("2020-11-16");

    public static Vote getNew() {
        return new Vote(null, LocalDate.now());
    }

    public static Vote getWithId() {
        Vote vote = new Vote(VOTE_ID, LocalDate.of(2020, 11, 18));
        return vote;
    }
}
