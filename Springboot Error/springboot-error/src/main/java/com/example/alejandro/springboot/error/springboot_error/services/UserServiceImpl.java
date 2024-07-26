package com.example.alejandro.springboot.error.springboot_error.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.alejandro.springboot.error.springboot_error.models.domain.User;

@Service
public class UserServiceImpl implements UserService{

    private List<User> users;

    public UserServiceImpl(){
        this.users = new ArrayList<>();
        users.add( new User("Yadira", "Barajas", 1L) );
        users.add( new User("Alejandro", "Romero", 2L) );
        users.add( new User("Emmanuel", "Saldana", 3L) );
        users.add( new User("Sahira", "Toro", 4L) );
        users.add( new User("Ale", "Gutierrez", 5L) );
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        for (User u : users) {
            if( u.getId().equals(id)) {
                user = u; 
                break;
            }
        }
        return Optional.ofNullable(user);
    }

}
