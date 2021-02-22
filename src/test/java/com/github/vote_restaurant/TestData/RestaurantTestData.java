package com.github.vote_restaurant.TestData;

import com.github.vote_restaurant.TestMatcher;
import com.github.vote_restaurant.model.Restaurant;

import static com.github.vote_restaurant.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = START_SEQ + 6;
    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Restaurant.class, "menus");
    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "rest2");

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }
}
