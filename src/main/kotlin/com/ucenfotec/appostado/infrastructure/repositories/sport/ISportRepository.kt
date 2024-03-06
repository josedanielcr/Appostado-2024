package com.ucenfotec.appostado.infrastructure.repositories.sport

import com.ucenfotec.appostado.core.domain.entities.Sport
import java.util.concurrent.CompletableFuture

interface ISportRepository {
    fun getSportById(sportId : String): CompletableFuture<Sport>
    fun getSports(): CompletableFuture<List<Sport>>
    fun createSport(sport: Sport): CompletableFuture<Sport>
    fun updateSport(sportId: String, sport: Sport): CompletableFuture<Sport>
    fun deleteSport(sportId: String): CompletableFuture<Boolean>
}