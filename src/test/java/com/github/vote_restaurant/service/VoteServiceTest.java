package com.github.vote_restaurant.service;

import com.github.vote_restaurant.TestData.UserTestData;
import com.github.vote_restaurant.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vote_restaurant.TestData.UserTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findById(){
        User user = userService.findById(USER_ID);
        USER_MATCHER.assertMatch(user, user1);
    }

    @Test
    public void create() {
        User created = userService.create(UserTestData.getNew());
        int newId = created.getId();
        User newUser = UserTestData.getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
    }
}