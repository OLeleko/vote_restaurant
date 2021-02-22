package com.github.vote_restaurant.service;


import com.github.vote_restaurant.TestData.RestaurantTestData;
import com.github.vote_restaurant.model.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vote_restaurant.TestData.RestaurantTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void findById() {
        Restaurant restaurant = restaurantService.findById(RESTAURANT_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
    public void create() {
        Restaurant created = restaurantService.create(RestaurantTestData.getNew());
        int newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
    }

}