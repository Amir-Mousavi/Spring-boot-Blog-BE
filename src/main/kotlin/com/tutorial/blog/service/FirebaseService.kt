package com.tutorial.blog.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import org.springframework.stereotype.Service
import java.io.FileInputStream

@Service
class FirebaseService {

    init {
        val builder = FirebaseOptions.builder()
        val options: FirebaseOptions =
            builder.setCredentials(GoogleCredentials.fromStream(FileInputStream("./src/main/resources/firebase.json")))
                .build()
        FirebaseApp.initializeApp(options)
    }

    fun getFirebaseToken(idToken: String?): FirebaseToken? {
        if (idToken == null) {
            return null
        }

        return FirebaseAuth.getInstance().verifyIdToken(idToken)
    }
}