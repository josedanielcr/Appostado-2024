package com.ucenfotec.appostado.core.application.common.interfaces.dog

import com.ucenfotec.appostado.core.application.dtos.dog.DogDTO
import com.ucenfotec.appostado.core.application.dtos.dog.DogDetailDTO
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture

interface IDogService {
    fun getDogById(dogId : String): CompletableFuture<DogDetailDTO>
    fun getDogs(): CompletableFuture<List<DogDetailDTO>>
    fun createDog(dog: DogDTO): CompletableFuture<DogDetailDTO>
    fun updateDog(dogId: String, dog: DogDTO): CompletableFuture<DogDetailDTO>
    fun deleteDog(dogId: String): CompletableFuture<Boolean>
}