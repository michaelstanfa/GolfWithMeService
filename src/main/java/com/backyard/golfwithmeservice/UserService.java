
package com.backyard.golfwithmeservice;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firestore.v1.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * User: Quinten
 * Date: 29-4-2014
 * Time: 17:04
 *
 * @author Quinten De Swaef
 */
@Service
@Transactional
public class UserService {


    @Autowired
    private Firestore db;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    public User addUserViaFirebase(User user) throws ExecutionException, InterruptedException {

//        ApiFuture<WriteResult> future = db.collection("users").document().set(user);
        DocumentReference doc = db.collection("users").document();
        user.setId(doc.getId());
        ApiFuture<WriteResult> future = doc.set(user);
        return user;
    }
}