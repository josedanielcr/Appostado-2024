package com.ucenfotec.appostado.infrastructure.repositories.dog

import com.ucenfotec.appostado.core.domain.entities.Dog
import java.util.concurrent.CompletableFuture

interface IDogRepository {
    fun getDog(): Dog
}