package com.github.vote_restaurant.TestData;

import com.github.vote_restaurant.TestMatcher;
import com.github.vote_restaurant.model.Menu;

import java.time.LocalDate;

import static com.github.vote_restaurant.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {

    public static final TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Menu.class, "restaurant", "meals");
    public static final int MENU_ID = START_SEQ + 8;
    public static LocalDate date = LocalDate.parse("2020-11-13");
    public static LocalDate date2 = LocalDate.parse("2020-11-16");
    public static final Menu menu1 = new Menu(MENU_ID, date2);

    public static Menu getNew() {
        return new Menu(null, date);
    }
}
