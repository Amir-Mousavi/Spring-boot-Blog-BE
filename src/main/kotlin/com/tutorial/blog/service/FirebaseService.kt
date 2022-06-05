package com.tutorial.blog.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseToken
import org.apache.commons.io.IOUtils
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

class FirebaseCredential {
    var type: String? = null
    var project_id: String? = null
    var private_key_id: String? = null
    var private_key: String? = null
    var client_email: String? = null
    var client_id: String? = null
    var auth_uri: String? = null
    var token_uri: String? = null
    var auth_provider_x509_cert_url: String? = null
    var client_x509_cert_url: String? = null
}

@Service
class FirebaseService(val environment: Environment) {

    init {

        val privateKey: String = environment.getRequiredProperty("FIREBASE_PRIVATE_KEY").replace("\"", "").replace("\\n", "\n")

        val firebaseCredential = FirebaseCredential()
        firebaseCredential.type = environment.getRequiredProperty("FIREBASE_TYPE")
        firebaseCredential.project_id = environment.getRequiredProperty("FIREBASE_PROJECT_ID")
        firebaseCredential.private_key_id = environment.getRequiredProperty("FIREBASE_PRIVATE_KEY_ID")
        firebaseCredential.private_key = privateKey
        firebaseCredential.client_id = environment.getRequiredProperty("FIREBASE_CLIENT_ID")
        firebaseCredential.client_email = environment.getRequiredProperty("FIREBASE_CLIENT_EMAIL")
        firebaseCredential.auth_uri = environment.getRequiredProperty("FIREBASE_AUTH_URI")
        firebaseCredential.token_uri = environment.getRequiredProperty("FIREBASE_TOKEN_URI")
        firebaseCredential.auth_provider_x509_cert_url = environment.getRequiredProperty("FIREBASE_AUTH_PROVIDER_X509_CERT_URL")
        firebaseCredential.client_x509_cert_url = environment.getRequiredProperty("FIREBASE_CLIENT_X509_CERT_URL")

        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(firebaseCredential)
        val inputStream = IOUtils.toInputStream(jsonString)

        val builder = FirebaseOptions.builder()
        val options: FirebaseOptions =
            builder.setCredentials(GoogleCredentials.fromStream(inputStream))
                .build()
        FirebaseApp.initializeApp(options)
    }

    fun getFirebaseToken(idToken: String?): FirebaseToken? {
        if (idToken == null) {
            return null
        }

        return try {
            FirebaseAuth.getInstance().verifyIdToken(idToken)
        }catch (ex: FirebaseAuthException) {
            null
        }
    }
}