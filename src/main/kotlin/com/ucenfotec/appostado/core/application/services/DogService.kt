package com.ucenfotec.appostado.core.application.services

import com.ucenfotec.appostado.core.application.common.exceptions.DogNotFoundException
import com.ucenfotec.appostado.core.application.common.interfaces.IDogService
import com.ucenfotec.appostado.core.application.dtos.dog.DogDetailDTO
import com.ucenfotec.appostado.core.application.mappings.dog.IDogMapper
import com.ucenfotec.appostado.core.domain.entities.Dog
import com.ucenfotec.appostado.infrastructure.repositories.dog.IDogRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class DogServiceImpl(
    val dogMapper : IDogMapper,
    val dogRepository: IDogRepository
) : IDogService {

    override fun getDog(): CompletableFuture<DogDetailDTO> {
        return CompletableFuture.supplyAsync {
            Thread.sleep(2000) // Delay for 2 seconds for testing
            val dog = dogRepository.getDog().join()
            dogMapper.dogToDogDetailDTO(dog)
            //throw DogNotFoundException(); //use this to test the global exception handler
            throw DogNotFoundException(additionalDetails = mapOf("dog" to dog)); //to add additional details
        }
    }
}