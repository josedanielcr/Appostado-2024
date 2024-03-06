package com.ucenfotec.appostado.core.application.mappings.sport

import com.ucenfotec.appostado.core.application.dtos.sport.SportDTO
import com.ucenfotec.appostado.core.application.dtos.sport.SportDetailDTO
import com.ucenfotec.appostado.core.domain.entities.Sport
import org.mapstruct.Mapper
@Mapper(componentModel = "spring")
interface ISportMapper {
    fun sportToSportDetailDTO(sport: Sport): SportDetailDTO
    fun sportDTOToSport(sportDTO: SportDTO): Sport
    fun sportDetailDTOToSport(sportDetailDTO: SportDetailDTO): Sport
}