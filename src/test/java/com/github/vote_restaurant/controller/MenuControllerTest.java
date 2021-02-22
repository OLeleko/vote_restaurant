package com.github.vote_restaurant.controller;

import com.github.vote_restaurant.TestData.MenuTestData;
import com.github.vote_restaurant.model.Menu;
import com.github.vote_restaurant.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.vote_restaurant.TestData.MenuTestData.MENU_MATCHER;
import static com.github.vote_restaurant.TestData.RestaurantTestData.RESTAURANT_ID;
import static com.github.vote_restaurant.TestData.UserTestData.admin1;
import static com.github.vote_restaurant.TestUtil.userHttpBasic;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTest extends AbstractControllerTest {

    private static final String REST_MENU_URL = "/admin/menus";

    @Autowired
    private MenuService service;


    @Test
    public void findByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_MENU_URL + '/' + "filter")
                .param("date", String.valueOf(MenuTestData.date2))
                .with(userHttpBasic(admin1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(3)));
    }

    @Test
    public void create() throws Exception {
        Menu newMenu = MenuTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_MENU_URL + "?restaurantId=" + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin1))
                .content(writeValue(newMenu)))
                .andExpect(status().isCreated())
                .andDo(print());
        Menu created = readFromJson(action, Menu.class);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
    }
}