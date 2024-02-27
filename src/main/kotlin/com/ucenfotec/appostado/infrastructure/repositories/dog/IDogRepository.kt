package com.ucenfotec.appostado.infrastructure.repositories.dog

import com.ucenfotec.appostado.core.domain.entities.Dog
import java.util.concurrent.CompletableFuture

interface IDogRepository {
    fun getDogById(dogId : String): CompletableFuture<Dog>
    fun getDogs(): CompletableFuture<List<Dog>>
    fun createDog(dog: Dog): CompletableFuture<Dog>
    fun updateDog(dogId: String, dog: Dog): CompletableFuture<Dog>
    fun deleteDog(dogId: String): CompletableFuture<Boolean>
}