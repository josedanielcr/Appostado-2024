package com.ucenfotec.appostado.core.application.common.interfaces

import com.ucenfotec.appostado.core.application.dtos.dog.DogDetailDTO
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture

interface IDogService {
    fun getDog(): CompletableFuture<DogDetailDTO>
}