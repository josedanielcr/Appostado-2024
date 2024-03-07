package com.ucenfotec.appostado.core.application.mappings.team

import com.ucenfotec.appostado.core.application.dtos.team.TeamDTO
import com.ucenfotec.appostado.core.application.dtos.team.TeamDetailDTO
import com.ucenfotec.appostado.core.domain.entities.Team
import org.mapstruct.Mapper
@Mapper(componentModel = "spring")
interface ITeamMapper {
    fun teamToTeamDetailDTO(team: Team): TeamDetailDTO
    fun teamDTOToTeam(teamDTO: TeamDTO): Team
    fun teamDetailDTOToTeam(teamDetailDTO: TeamDetailDTO): Team
}