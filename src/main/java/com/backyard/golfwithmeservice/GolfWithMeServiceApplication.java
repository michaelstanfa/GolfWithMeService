package com.backyard.golfwithmeservice;

import com.backyard.golfwithmeservice.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
public class GolfWithMeServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(GolfWithMeServiceApplication.class, args);

//		UserService service = new UserService();
//		service.addUser(new User("Michael", "Stanfa", "stanfa.michael@gmail.com"));

	}

}
