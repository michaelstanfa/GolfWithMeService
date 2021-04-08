package com.backyard.golfwithmeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Component
public interface UserRepository extends JpaRepository<User, UUID> {

}
