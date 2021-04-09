
package com.backyard.golfwithmeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firestore.v1.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
    private ObjectMapper objectMapper;

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

        DocumentReference doc = db.collection("users").document();
        user.setId(doc.getId());
        ApiFuture<WriteResult> future = doc.set(user);
        return user;
    }

    public User getUserViaFirebaseWithIdOrName(String idOrName) throws ExecutionException, InterruptedException {

        User user = null;

        if(20 == idOrName.length()) {
            user = getUserViaFirebaseWithId(idOrName);
        } else {
            user = getUserViaFirebaseWithUsername(idOrName);
        }

        return user;

    }

    public User getUserViaFirebaseWithId(String id) throws ExecutionException, InterruptedException {

        DocumentReference doc = db.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = doc.get();
        DocumentSnapshot document = future.get();
        if(document.exists()) {
            return objectMapper.convertValue(document.getData(), User.class);
        }

        return null;

    }

    public User getUserViaFirebaseWithUsername(String userName) throws ExecutionException, InterruptedException {

        CollectionReference users = db.collection("users");

        Query query = users.whereEqualTo("userName", userName);

        DocumentSnapshot document = query.get().get().getDocuments().get(0);

        if(document.exists()) {
            return objectMapper.convertValue(document.getData(), User.class);
        }

        return null;

    }

    public List<User> getUsersViaFirebase() throws ExecutionException, InterruptedException {

        return getAllUsersFromFb()
                .stream()
                .map(d -> objectMapper.convertValue(d.getData(), User.class))
                .collect(Collectors.toList());

    }

    public void deleteAllUsersViaFb() throws ExecutionException, InterruptedException {

        getAllUsersFromFb()
                .stream()
                .forEach(
                    d -> d.getReference().delete()
                );

    }

    private List<QueryDocumentSnapshot> getAllUsersFromFb() throws InterruptedException, ExecutionException {
        return db.collection("users")
                .get()
                .get()
                .getDocuments();
    }
}