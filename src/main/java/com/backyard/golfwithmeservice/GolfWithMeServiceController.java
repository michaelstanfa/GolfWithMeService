package com.backyard.golfwithmeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class GolfWithMeServiceController {

    private GolfWithMeServiceController() {
        //empty constructor
    }

    @Autowired
    public UserService userService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public String home() {
        return "hello world";
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public List<User> getUsers() {
        return userService.getAllUsers() ;
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public User getUsers(@PathVariable UUID id) {
        return userService.getUserById(id) ;
    }

    @PostMapping(
        value = "/user",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if(getUsers().contains(user)) {
            Map map = new HashMap<>();
            map.put("error", "Username or email already in use, try again.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        return ResponseEntity.ok(userService.addUser(user));
    }

}
