package com.ucenfotec.appostado.core.application.common.interfaces

import com.ucenfotec.appostado.core.application.dtos.dog.DogDetailDTO
import org.springframework.scheduling.annotation.Async

interface IDogService {
    fun getDog(): DogDetailDTO
}