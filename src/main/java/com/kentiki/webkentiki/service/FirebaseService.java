package com.kentiki.webkentiki.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseService {

    @PostConstruct
    public void initialize() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("firebase_config.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://webkentiki-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
