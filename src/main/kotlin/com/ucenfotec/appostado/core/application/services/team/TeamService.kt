package com.ucenfotec.appostado.core.application.services.team

import com.google.cloud.Timestamp
import com.ucenfotec.appostado.core.application.common.interfaces.team.ITeamService
import com.ucenfotec.appostado.core.application.dtos.team.TeamDTO
import com.ucenfotec.appostado.core.application.dtos.team.TeamDetailDTO
import com.ucenfotec.appostado.core.application.mappings.team.ITeamMapper
import com.ucenfotec.appostado.infrastructure.repositories.team.ITeamRepository
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class TeamServiceImpl(
        val teamMapper : ITeamMapper,
        val teamRepository: ITeamRepository
) : ITeamService {

    override fun getTeamById(teamId : String): CompletableFuture<TeamDetailDTO> {
        return CompletableFuture.supplyAsync {
            //business logic
            val team = teamRepository.getTeamById(teamId).join()
            teamMapper.teamToTeamDetailDTO(team)
        }
    }

    override fun getTeams(): CompletableFuture<List<TeamDetailDTO>> {
        return CompletableFuture.supplyAsync {
            val teams = teamRepository.getTeams().join()
            teams.map { teamMapper.teamToTeamDetailDTO(it) }
        }
    }

    override fun getTeamsBySport(sportId : String): CompletableFuture<List<TeamDetailDTO>> {
        return CompletableFuture.supplyAsync {
                //business logic
                val teams = teamRepository.getTeamsBySport(sportId).join()
                teams.map { teamMapper.teamToTeamDetailDTO(it) }

            }
    }

    override fun createTeam(team: TeamDTO): CompletableFuture<TeamDetailDTO> {
        return CompletableFuture.supplyAsync {
            val teamEntity = teamMapper.teamDTOToTeam(team)
            teamEntity.createdAt = Timestamp.now()
            teamEntity.updatedAt = Timestamp.now()
            val createdTeam = teamRepository.createTeam(teamEntity).join()
            teamMapper.teamToTeamDetailDTO(createdTeam)
        }
    }

    override fun updateTeam(teamId: String, team: TeamDTO): CompletableFuture<TeamDetailDTO> {
        return CompletableFuture.supplyAsync {
            val existingTeam = teamRepository.getTeamById(teamId).join()
            val teamEntity = teamMapper.teamDTOToTeam(team).apply {
                createdAt = existingTeam.createdAt
                updatedAt = Timestamp.now()
                id = existingTeam.id
            }
            val updatedSport = teamRepository.updateTeam(teamId, teamEntity).join()
            teamMapper.teamToTeamDetailDTO(updatedSport)
        }
    }

    override fun deleteTeam(teamId: String): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            teamRepository.deleteTeam(teamId).join()
        }
    }
}