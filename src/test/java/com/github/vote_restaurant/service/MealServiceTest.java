package com.github.vote_restaurant.service;

import com.github.vote_restaurant.model.Meal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vote_restaurant.TestData.MealTestData.*;
import static com.github.vote_restaurant.TestData.MenuTestData.MENU_ID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal created = service.create(getNew(), MENU_ID);
        int newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
    }

    @Test
    public void findById() {
        Meal meal = service.findById(MEAL_ID);
        MEAL_MATCHER.assertMatch(meal, meal1);
    }

}