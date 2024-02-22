package com.ucenfotec.appostado.infrastructure.repositories.dog

import com.ucenfotec.appostado.core.domain.entities.Dog
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Async
@Repository
class DogRepository : IDogRepository {
    override fun getDog(): Dog {
        //do some database stuff and save it and return it
        return Dog("Firulais", 3);
    }
}