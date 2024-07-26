package com.alejandro.curso.springboot.webapp.springboot_web.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.curso.springboot.webapp.springboot_web.models.User;
import com.alejandro.curso.springboot.webapp.springboot_web.models.dto.UserDTO;

import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping("/api")
public class UserRestController {

     @GetMapping(path = "/details")
     public UserDTO details(){

        UserDTO userDTO = new UserDTO();
        User user = new User("Alejandro", "Romero Gonzalez");
        userDTO.setUser(user);
        userDTO.setTitle("Hola Mundo Spring Boot");

    //      Map<String, Object> body= new HashMap<>();
    //      body.put("title", "Hola Mundo Spring Boot");
    //      body.put("user", user);

         return userDTO;
    }

    @GetMapping("/list")
    public List<User> list(){
        User user = new User("Alejandro", "Romero Gonzalez");
        User user2 = new User("Pepe", "Guzman");
        User user3 = new User("Andres", "Doe");
        User user4 = new User("Jhon", "Doe");

        List<User> users = Arrays.asList(user, user2, user3, user4);
        // List<User> users = new ArrayList<>();
        // users.add(user);
        // users.add(user2);
        // users.add(user3);
        // users.add(user4);
        
        return users;
    }
    

    @GetMapping(path = "/details-map")
    public Map<String, Object> detailsMap(){

        User user = new User("Alejandro", "Romero Gonzalez");
        Map<String, Object> body= new HashMap<>();

        body.put("title", "Hola Mundo Spring Boot");
        body.put("user", user);

        return body;
    }
}