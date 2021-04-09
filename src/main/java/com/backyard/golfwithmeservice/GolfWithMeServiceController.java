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
import java.util.concurrent.ExecutionException;

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

    @PostMapping(
            value = "/userfb",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public ResponseEntity<?> createUserFb(@RequestBody User user) throws ExecutionException, InterruptedException {
        if(getUsers().contains(user)) {
            Map map = new HashMap<>();
            map.put("error", "Username or email already in use, try again.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        return ResponseEntity.ok(userService.addUserViaFirebase(user));
    }

    @GetMapping(
            value = "/userfb/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public ResponseEntity<?> getUserFb(@PathVariable String id) throws ExecutionException, InterruptedException {

        User user = userService.getUserViaFirebaseWithId(id);
        if(null != user) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping(
            value = "/usersfb",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public ResponseEntity<?> getUserFb() throws ExecutionException, InterruptedException {

        List<User> users = userService.getUsersViaFirebase();

        return ResponseEntity.ok(users);

    }

    @DeleteMapping(
            value = "/usersfb",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public ResponseEntity<?> deleteUsersFb() throws ExecutionException, InterruptedException {
        try {
            userService.deleteAllUsersViaFb();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Issue deleting users: " + e);
            throw new RuntimeException(e);
        }

    }


}
