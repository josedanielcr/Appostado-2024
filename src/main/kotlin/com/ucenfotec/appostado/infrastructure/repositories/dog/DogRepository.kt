package com.ucenfotec.appostado.infrastructure.repositories.dog

import com.google.cloud.firestore.Firestore
import com.ucenfotec.appostado.core.application.common.exceptions.DogNotFoundException
import com.ucenfotec.appostado.core.domain.entities.Dog
import kotlinx.coroutines.CoroutineScope
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import java.util.concurrent.CompletableFuture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.await
import kotlinx.coroutines.future.future
import kotlinx.coroutines.withContext

@Async
@Repository
class DogRepository(private val firestore : Firestore) : IDogRepository {

    private val DOGS_COLLECTION: String = "dogs"
    @Async
    override fun getDogById(dogId : String): CompletableFuture<Dog> {
        return CompletableFuture.supplyAsync {
            val documentSnapshot = firestore.collection(DOGS_COLLECTION).document(dogId).get().get()
            if (!documentSnapshot.exists()) {
                throw DogNotFoundException(additionalDetails = mapOf("dogId" to dogId));
            }
            Dog.fromDocumentSnapshot(documentSnapshot)
        }
    }

    override fun getDogs(): CompletableFuture<List<Dog>> {
        return CompletableFuture.supplyAsync {
            val querySnapshot = firestore.collection(DOGS_COLLECTION).get().get()
            val dogs = mutableListOf<Dog>()
            for (document in querySnapshot.documents) {
                dogs.add(Dog.fromDocumentSnapshot(document))
            }
            dogs
        }
    }

    override fun createDog(dog: Dog): CompletableFuture<Dog> {
        return CompletableFuture.supplyAsync {
            val documentReference = firestore.collection(DOGS_COLLECTION).document(dog.id)
            documentReference.set(dog).get()
            dog
        }
    }

    override fun updateDog(dogId: String, dog: Dog): CompletableFuture<Dog> {
        return CompletableFuture.supplyAsync {
            firestore.collection(DOGS_COLLECTION).document(dogId).set(dog).get()
            getDogById(dogId).join()
        }
    }

    override fun deleteDog(dogId: String): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            val documentReference = firestore.collection(DOGS_COLLECTION).document(dogId)
            val deleteResult = documentReference.delete().get()
            deleteResult != null
        }
    }
}