package com.ucenfotec.appostado.core.application.common.interfaces

import com.ucenfotec.appostado.core.application.dtos.sport.SportDTO
import com.ucenfotec.appostado.core.application.dtos.sport.SportDetailDTO
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture

interface ISportService {
    fun getSportById(sportId : String): CompletableFuture<SportDetailDTO>
    fun getSports(): CompletableFuture<List<SportDetailDTO>>
    fun createSport(sportId: SportDTO): CompletableFuture<SportDetailDTO>
    fun updateSport(sportId: String, sport: SportDTO): CompletableFuture<SportDetailDTO>
    fun deleteSport(sportId: String): CompletableFuture<Boolean>
}