package com.ucenfotec.appostado.core.application.services.sport

import com.google.cloud.Timestamp
import com.ucenfotec.appostado.core.application.common.interfaces.sport.ISportService
import com.ucenfotec.appostado.core.application.dtos.sport.SportDTO
import com.ucenfotec.appostado.core.application.dtos.sport.SportDetailDTO
import com.ucenfotec.appostado.core.application.mappings.sport.ISportMapper
import com.ucenfotec.appostado.infrastructure.repositories.sport.ISportRepository
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class SportServiceImpl(
        val sportMapper : ISportMapper,
        val sportRepository: ISportRepository
) : ISportService {

    override fun getSportById(sportId : String): CompletableFuture<SportDetailDTO> {
        return CompletableFuture.supplyAsync {
            //business logic
            val sport = sportRepository.getSportById(sportId).join()
            sportMapper.sportToSportDetailDTO(sport)
        }
    }

    override fun getSports(): CompletableFuture<List<SportDetailDTO>> {
        return CompletableFuture.supplyAsync {
            val sports = sportRepository.getSports().join()
            sports.map { sportMapper.sportToSportDetailDTO(it) }
        }
    }

    override fun createSport(sport: SportDTO): CompletableFuture<SportDetailDTO> {
        return CompletableFuture.supplyAsync {
            val sportEntity = sportMapper.sportDTOToSport(sport)
            sportEntity.createdAt = Timestamp.now()
            sportEntity.updatedAt = Timestamp.now()
            val createdSport = sportRepository.createSport(sportEntity).join()
            sportMapper.sportToSportDetailDTO(createdSport)
        }
    }

    override fun updateSport(sportId: String, sport: SportDTO): CompletableFuture<SportDetailDTO> {
        return CompletableFuture.supplyAsync {
            val existingSport = sportRepository.getSportById(sportId).join()
            val sportEntity = sportMapper.sportDTOToSport(sport).apply {
                createdAt = existingSport.createdAt
                updatedAt = Timestamp.now()
                id = existingSport.id
            }
            val updatedSport = sportRepository.updateSport(sportId, sportEntity).join()
            sportMapper.sportToSportDetailDTO(updatedSport)
        }
    }

    override fun deleteSport(sportId: String): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            sportRepository.deleteSport(sportId).join()
        }
    }
}