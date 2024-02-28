package com.ucenfotec.appostado.infrastructure.configuration

import com.azure.security.keyvault.secrets.SecretClient
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class FirebaseConfig(
    private val secretClient: SecretClient,
    private val objectMapper: ObjectMapper
) {

    @Bean
    fun firestore(): Firestore {

        val secretBundle = secretClient.getSecret("firebase-admin-sdk-config")
        val secretValue = secretBundle.value
        val firebaseConfig: Map<String, Any> = objectMapper.readValue(secretValue)
        val inputStream = objectMapper.writeValueAsBytes(firebaseConfig).inputStream()

        inputStream.use { serviceAccountStream ->
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