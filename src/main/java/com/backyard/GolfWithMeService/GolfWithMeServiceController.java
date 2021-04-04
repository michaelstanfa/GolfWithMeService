package com.backyard.GolfWithMeService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
public class GolfWithMeServiceController {

    private GolfWithMeServiceController() {
        //empty constructor
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public String home() {
        return "hello world";
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public String getUsers() {
        return "these are the users";
    }

    @GetMapping(value = "/message/{message}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public String getMessage(@PathVariable String message) {
        return "This is your message: " + message;
    }

}
