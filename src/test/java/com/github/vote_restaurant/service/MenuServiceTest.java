package com.github.vote_restaurant.service;


import com.github.vote_restaurant.model.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.vote_restaurant.TestData.MenuTestData.*;
import static com.github.vote_restaurant.TestData.RestaurantTestData.RESTAURANT_ID;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void create() {
        Menu created = menuService.create(getNew(), RESTAURANT_ID);
        int newId = created.getId();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
    }

    @Test
    public void findById() {
        Menu menu = menuService.findById(MENU_ID);
        MENU_MATCHER.assertMatch(menu, menu1);
    }

    @Test
    public void findByDate() {
        List<Menu> menuList = menuService.findByDate(date2);
        assertThat(menuList).hasSize(3);
    }
}