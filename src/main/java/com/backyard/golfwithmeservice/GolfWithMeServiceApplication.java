package com.backyard.golfwithmeservice;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GolfWithMeServiceApplication {

	@Bean
	public Firestore firestore() {
		try {

			// Use the application default credentials
			GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(credentials)
					.setProjectId("golfwithmeservice")
					.build();
			FirebaseApp.initializeApp(options);

			return FirestoreClient.getFirestore();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {

		SpringApplication.run(GolfWithMeServiceApplication.class, args);

	}
}
