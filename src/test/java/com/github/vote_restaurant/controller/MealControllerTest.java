package com.github.vote_restaurant.controller;

import com.github.vote_restaurant.TestData.MealTestData;
import com.github.vote_restaurant.model.Meal;
import com.github.vote_restaurant.service.MealService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.vote_restaurant.TestData.MealTestData.MEAL_ID;
import static com.github.vote_restaurant.TestData.MealTestData.MEAL_MATCHER;
import static com.github.vote_restaurant.TestData.UserTestData.admin1;
import static com.github.vote_restaurant.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MealControllerTest extends AbstractControllerTest {

    private static final String REST_MEAL_URL = "/admin/meals";

    @Autowired
    private MealService service;

    @Test
    public void findById() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_MEAL_URL + '/' + MEAL_ID)
                .with(userHttpBasic(admin1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value((MEAL_ID)));
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = MealTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_MEAL_URL + "?menuId=" + MEAL_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin1))
                .content(writeValue(newMeal)))
                .andExpect(status().isCreated())
                .andDo(print());

        Meal created = readFromJson(action, Meal.class);
        int newId = created.id();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
    }

}