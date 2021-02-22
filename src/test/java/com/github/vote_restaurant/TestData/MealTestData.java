package com.github.vote_restaurant.TestData;

import com.github.vote_restaurant.TestMatcher;
import com.github.vote_restaurant.model.Meal;

import java.math.BigDecimal;

import static com.github.vote_restaurant.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final TestMatcher<Meal> MEAL_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Meal.class, "menu");
    public static final int MEAL_ID = START_SEQ + 17;
    public static final Meal meal1 = new Meal(MEAL_ID, "meal1_1_1", new BigDecimal("3.23"));

    public static Meal getNew() {
        return new Meal(null, "New_meal", new BigDecimal("15.01"));
    }
}
