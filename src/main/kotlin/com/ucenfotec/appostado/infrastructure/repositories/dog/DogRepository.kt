package com.ucenfotec.appostado.infrastructure.repositories.dog

import com.ucenfotec.appostado.core.domain.entities.Dog
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Async
@Repository
class DogRepository : IDogRepository {
    @Async
    override fun getDog(): CompletableFuture<Dog> {
        return CompletableFuture.supplyAsync {
            // Perform your database operation here
            Dog("Firulais", 3)
        }
    }
}