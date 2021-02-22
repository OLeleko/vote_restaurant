Meal vote application

REST API documentation is available via Swagger: http://localhost:8080/swagger-ui.html 

The user, restaurant, meal and menu controllers are intended to be used by admins only.
Each of those controllers is designed to create-update-delete of appropriate objects (user, restaurant, meal, menu). 
Menu object is designed to crossconnect restaurant and meal objects.

Any authenticated user can access application with Vote controller. TodayMenus method of VoteController can be used to get menus for current day.
Every menu is identified by the restaurant name.
Here are the curl requests for testing:

#### get all menus for 2020-11-17
curl -s http://localhost:8080/admin/menus/filter?date=2020-11-17 --user admin1:52345

#### Create vote of user1
curl -s -X POST -d "{}" -H "Content-Type:application/json" http://localhost:8080/votes?menuId=100016 --user user1:12345

#### get all votes of user1
curl -s http://localhost:8080/votes --user user1:12345

Technical requirements are the following:

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.
The task is:
Build a voting system for deciding where to have lunch.
2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day: 
If it is before 11:00 we assume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides a new menu each day.
As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

P.S.: Make sure everything works with latest version that is on github :)
P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.
