package com.github.vote_restaurant.controller;


import com.github.vote_restaurant.TestData.VoteTestData;
import com.github.vote_restaurant.model.Vote;
import com.github.vote_restaurant.service.MenuService;
import com.github.vote_restaurant.service.VoteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.vote_restaurant.TestData.UserTestData.admin1;
import static com.github.vote_restaurant.TestData.UserTestData.user1;
import static com.github.vote_restaurant.TestData.VoteTestData.VOTE_ID;
import static com.github.vote_restaurant.TestData.VoteTestData.VOTE_MATCHER;
import static com.github.vote_restaurant.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_VOTE_URL = "/votes";

    @Autowired
    private VoteService service;

    @Autowired
    private MenuService menuService;

    @Test
    public void findById() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_VOTE_URL + '/' + VOTE_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value((VOTE_ID)));
        ;
    }

    @Test
    public void create() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_VOTE_URL + "?menuId=100010")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin1))
                .content(writeValue(newVote)))
                .andExpect(status().isCreated())
                .andDo(print());

        Vote created = readFromJson(action, Vote.class);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
    }
}