package com.example.alejandro.springboot.error.springboot_error.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alejandro.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.example.alejandro.springboot.error.springboot_error.models.domain.User;
import com.example.alejandro.springboot.error.springboot_error.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping
    public String index(){
        //int value = 100 / 0;
        int value = Integer.parseInt("holaaaa");
        return "ok 200" + value;
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable Long id){
        User user =  service.findById(id).orElseThrow(() -> new UserNotFoundException("Error el usuario no existe!") );
        //Optional<User> optionalUser = service.findById(id); 
        //if(optionalUser.isEmpty()){
        //    return ResponseEntity.notFound().build();
        //}
        //System.out.println(user.getLastname());
        return user;
    }

}
