package com.github.vote_restaurant.controller;


import com.github.vote_restaurant.model.User;
import com.github.vote_restaurant.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.vote_restaurant.TestData.UserTestData.*;
import static com.github.vote_restaurant.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest {

    private static final String REST_USER_URL = "/admin/users" + '/';

    @Autowired
    private UserService service;

    @Test
    public void findById() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_USER_URL + ADMIN_ID)
                .with(userHttpBasic(admin1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(("100003")));
    }

    @Test
    public void create() throws Exception {
        User newUser = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin1))
                .content(writeValue(newUser)))
                .andExpect(status().isCreated())
                .andDo(print());
        User created = readFromJson(action, User.class);
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
    }
}