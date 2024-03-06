package com.ucenfotec.appostado.infrastructure.repositories.dog

import com.google.cloud.firestore.Firestore
import com.ucenfotec.appostado.core.application.common.exceptions.SportNotFoundException
import com.ucenfotec.appostado.core.domain.entities.Sport
import com.ucenfotec.appostado.infrastructure.repositories.sport.ISportRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import java.util.concurrent.CompletableFuture

@Async
@Repository
class SportRepository(private val firestore : Firestore) : ISportRepository {

    private val SPORTS_COLLECTION: String = "sports"

    override fun getSportById(sportId : String): CompletableFuture<Sport> {
        return CompletableFuture.supplyAsync {
            val documentSnapshot = firestore.collection(SPORTS_COLLECTION).document(sportId).get().get()
            if (!documentSnapshot.exists()) {
                throw SportNotFoundException(additionalDetails = mapOf("sportId" to sportId));
            }
            Sport.fromDocumentSnapshot(documentSnapshot)
        }
    }

    override fun getSports(): CompletableFuture<List<Sport>> {
        return CompletableFuture.supplyAsync {
            val querySnapshot = firestore.collection(SPORTS_COLLECTION).get().get()
            val dogs = mutableListOf<Sport>()
            for (document in querySnapshot.documents) {
                dogs.add(Sport.fromDocumentSnapshot(document))
            }
            dogs
        }
    }

    override fun createSport(sport: Sport): CompletableFuture<Sport> {
        return CompletableFuture.supplyAsync {
            val documentReference = firestore.collection(SPORTS_COLLECTION).document(sport.id)
            documentReference.set(sport).get()
            getSportById(sport.id).join()
        }
    }

    override fun updateSport(sportId: String, sport: Sport): CompletableFuture<Sport> {
        return CompletableFuture.supplyAsync {
            firestore.collection(SPORTS_COLLECTION).document(sportId).set(sport).get()
            getSportById(sportId).join()
        }
    }

    override fun deleteSport(sportId: String): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            val documentReference = firestore.collection(SPORTS_COLLECTION).document(sportId)
            val deleteResult = documentReference.delete().get()
            deleteResult != null
        }
    }
}