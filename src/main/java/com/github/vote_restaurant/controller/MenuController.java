package com.github.vote_restaurant.controller;

import com.github.vote_restaurant.model.Menu;
import com.github.vote_restaurant.service.MenuService;
import com.github.vote_restaurant.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    @Autowired
    private MenuService service;

    @GetMapping()
    public List<Menu> findAll() {
        return service.findAll();
    }

    @GetMapping("/filter")
    public List<Menu> findByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.findByDate(date);
    }

    @GetMapping("/{id}")
    public Menu findById(@PathVariable int id) {
        Menu result = service.findById(id);
        if(result == null){
            throw new NotFoundException("Object not found");
        }
        return result;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody Menu menu, @RequestParam int restaurantId) {
        Menu created = service.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/menus/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
