package com.ucenfotec.appostado.infrastructure.repositories.user

import com.google.cloud.firestore.Firestore
import com.ucenfotec.appostado.core.application.common.exceptions.user.UserNotFoundException
import com.ucenfotec.appostado.core.domain.entities.User
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import java.util.concurrent.CompletableFuture

@Async
@Repository
class UserRepository(
    private val firestore: Firestore
): IUserRepository {
    private val USERS_COLLECTION: String = "users";
    override fun getUserById(userId: String): CompletableFuture<User> {
        return CompletableFuture.supplyAsync {
            val documentSnapshot = firestore.collection(USERS_COLLECTION).document(userId).get().get()
            if (!documentSnapshot.exists()) {
                throw UserNotFoundException(additionalDetails = mapOf("userId" to userId));
            }
            User.fromDocumentSnapshot(documentSnapshot)
        }
    }

    override fun getUserByEmail(email: String): CompletableFuture<User> {
        return CompletableFuture.supplyAsync {
            val querySnapshot = firestore.collection(USERS_COLLECTION).whereEqualTo("email", email).get().get()
            if (querySnapshot.isEmpty) {
                throw UserNotFoundException(additionalDetails = mapOf("email" to email));
            }
            User.fromDocumentSnapshot(querySnapshot.documents[0])
        }
    }

    override fun getUsers(): CompletableFuture<List<User>> {
        TODO("Not yet implemented")
    }

    override fun createUser(user: User): CompletableFuture<User> {
        return CompletableFuture.supplyAsync {
            val documentReference = firestore.collection(USERS_COLLECTION).document(user.id);
            documentReference.set(user).get();
            getUserById(user.id).join();
        }
    }

    override fun updateUser(userId: String, user: User): CompletableFuture<User> {
        TODO("Not yet implemented")
    }

    override fun deleteUser(userId: String): CompletableFuture<Boolean> {
        TODO("Not yet implemented")
    }
}