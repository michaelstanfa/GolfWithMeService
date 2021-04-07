package com.backyard.golfwithmeservice;

import com.backyard.golfwithmeservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(@Param("id") Long id);

}
