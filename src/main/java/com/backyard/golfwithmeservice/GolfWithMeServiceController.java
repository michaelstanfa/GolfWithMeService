package com.backyard.golfwithmeservice;

import com.backyard.golfwithmeservice.user.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
public class GolfWithMeServiceController {

    private GolfWithMeServiceController() {
        //empty constructor
    }

    private UserService userService;

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

    @PostMapping(
        value = "/user",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public void createUser(@RequestBody User user) {
       userService.addUser(user);
    }

}
