package com.github.vote_restaurant.TestData;

import com.github.vote_restaurant.TestMatcher;
import com.github.vote_restaurant.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.github.vote_restaurant.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class, "registered", "password");
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 3;
    public static final String sDate1 = "2020-05-02";
    public static final String sDate2 = "2020-08-13";
    public static Date date1 = new Date();
    public static  Date date2 = new Date();
    static {
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static final User user1 = new User(USER_ID, "user1", "12345", date1, true, false);
    public static User getNew() {
        return new User(null, "New1", "12345", new Date(), true, false);
    }
    public static final User admin1 = new User(ADMIN_ID, "admin1", "52345", date2, true, true);
}
