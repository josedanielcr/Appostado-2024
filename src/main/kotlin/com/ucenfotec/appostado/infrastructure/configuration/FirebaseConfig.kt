package com.ucenfotec.appostado.infrastructure.configuration

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FirebaseConfig {

    @Bean
    fun firestore(): Firestore {
        val serviceAccountPath = "src/main/resources/static/exampleapp-5e5e2-firebase-adminsdk-dtx8e-ea21123047.json"
        FileInputStream(serviceAccountPath).use { serviceAccountStream ->
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build()

            val firebaseApp = if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
            } else {
                FirebaseApp.getInstance()
            }

            return FirestoreClient.getFirestore(firebaseApp)
        }
    }
}
