package com.backyard.GolfWithMeService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
