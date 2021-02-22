package com.github.vote_restaurant.service;

import com.github.vote_restaurant.model.Restaurant;
import com.github.vote_restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.vote_restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public Restaurant findById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Restaurant> findAll(){
        return repository.findAll();
    }

    public void delete(int id){
        checkNotFoundWithId(repository.delete(id), id);
    }
}
