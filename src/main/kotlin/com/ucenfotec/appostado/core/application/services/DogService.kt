package com.ucenfotec.appostado.core.application.services

import com.ucenfotec.appostado.core.application.common.exceptions.DogNotFoundException
import com.ucenfotec.appostado.core.application.common.interfaces.IDogService
import com.ucenfotec.appostado.core.application.dtos.dog.DogDTO
import com.ucenfotec.appostado.core.application.dtos.dog.DogDetailDTO
import com.ucenfotec.appostado.core.application.mappings.dog.IDogMapper
import com.ucenfotec.appostado.core.domain.entities.Dog
import com.ucenfotec.appostado.infrastructure.repositories.dog.IDogRepository
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
class DogServiceImpl(
    val dogMapper : IDogMapper,
    val dogRepository: IDogRepository
) : IDogService {

    override fun getDogById(dogId : String): CompletableFuture<DogDetailDTO> {
        return CompletableFuture.supplyAsync {
            //business logic
            val dog = dogRepository.getDogById(dogId).join()
            dogMapper.dogToDogDetailDTO(dog)
        }
    }

    override fun getDogs(): CompletableFuture<List<DogDetailDTO>> {
        return CompletableFuture.supplyAsync {
            val dogs = dogRepository.getDogs().join()
            dogs.map { dogMapper.dogToDogDetailDTO(it) }
        }
    }

    override fun createDog(dog: DogDTO): CompletableFuture<DogDetailDTO> {
        return CompletableFuture.supplyAsync {
            val dogEntity = dogMapper.dogDTOToDog(dog)
            val createdDog = dogRepository.createDog(dogEntity).join()
            dogMapper.dogToDogDetailDTO(createdDog)
        }
    }

    override fun updateDog(dogId: String, dog: DogDetailDTO): CompletableFuture<DogDetailDTO> {
        return CompletableFuture.supplyAsync {
            val dogEntity = dogMapper.dogDetailDTOToDog(dog)
            val updatedDog = dogRepository.updateDog(dogId, dogEntity).join()
            dogMapper.dogToDogDetailDTO(updatedDog)
        }
    }

    override fun deleteDog(dogId: String): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            dogRepository.deleteDog(dogId).join()
        }
    }
}