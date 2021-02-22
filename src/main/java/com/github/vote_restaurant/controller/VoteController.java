package com.github.vote_restaurant.controller;

import com.github.vote_restaurant.model.Menu;
import com.github.vote_restaurant.model.User;
import com.github.vote_restaurant.model.Vote;
import com.github.vote_restaurant.service.CustomUserDetails;
import com.github.vote_restaurant.service.MenuService;
import com.github.vote_restaurant.service.VoteService;
import com.github.vote_restaurant.util.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    private static  final Logger LOGGER = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteService service;

    @Autowired
    private MenuService menuService;

    @GetMapping()
    public List<Vote> findAll(@ApiIgnore @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        int userId = user.getId();
        return service.findAll(userId);
    }

    @GetMapping("/{id}")
    public Vote findById(@ApiIgnore @AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable int id) {
        User user = customUserDetails.getUser();
        int userId = user.getId();
        Vote result = service.findById(id, userId);
        if(result == null){
            throw new NotFoundException("Object not found");
        }
        return result;
    }

    @GetMapping("/date")
    public Vote findByDate(@ApiIgnore @AuthenticationPrincipal CustomUserDetails customUserDetails,
                           @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = customUserDetails.getUser();
        int userId = user.getId();
        return service.findByDate(date, userId);
    }

    @GetMapping("/filter")
    public List<Vote> findBetween(@ApiIgnore @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                  @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        User user = customUserDetails.getUser();
        int userId = user.getId();
        return service.findBetween(start, end, userId);
    }

    @GetMapping("/menus")
    public List<Menu> todayMenus(@ApiIgnore @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return menuService.findByDate(LocalDate.now());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@ApiIgnore @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @RequestParam int menuId) {
        User user = customUserDetails.getUser();
        int userId = user.getId();
        Vote created = service.create(menuId, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}")
                .buildAndExpand(created.getId()).toUri();
        LOGGER.info("User " + user.getName() + " voted " + menuId);
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@ApiIgnore @AuthenticationPrincipal CustomUserDetails customUserDetails,
                       @RequestParam int menu_id) {
        User user = customUserDetails.getUser();
        int userId = user.getId();
        LOGGER.info("User " + user.getName() + " updated " + menu_id);
        service.update(menu_id, userId);
    }
}
