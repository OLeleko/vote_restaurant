package com.github.vote_restaurant.service;

import com.github.vote_restaurant.model.User;
import com.github.vote_restaurant.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.vote_restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(int id){
        return repository.findById(id).orElse(null);
    }

    public User create(User user){
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void update(User user){
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    public void delete(int id){
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void enable(int id, boolean enabled){
        User user = findById(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    public List<User> findAll(){
        return repository.findAll();
    }
}
