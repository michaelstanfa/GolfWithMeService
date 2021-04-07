package com.backyard.golfwithmeservice.user;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

public class User {

    @Id @GeneratedValue(generator = "uuid2")
    Long id;
    String firstName;
    String lastName;
    String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
