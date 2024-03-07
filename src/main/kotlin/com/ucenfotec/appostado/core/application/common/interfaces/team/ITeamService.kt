package com.ucenfotec.appostado.core.application.common.interfaces.team

import com.ucenfotec.appostado.core.application.dtos.team.TeamDTO
import com.ucenfotec.appostado.core.application.dtos.team.TeamDetailDTO
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture

interface ITeamService {
    fun getTeamById(teamId : String): CompletableFuture<TeamDetailDTO>
    fun getTeams(): CompletableFuture<List<TeamDetailDTO>>
    fun getTeamsBySport(sportId : String): CompletableFuture<List<TeamDetailDTO>>
    fun createTeam(team: TeamDTO): CompletableFuture<TeamDetailDTO>
    fun updateTeam(teamId: String, team: TeamDTO): CompletableFuture<TeamDetailDTO>
    fun deleteTeam(teamId: String): CompletableFuture<Boolean>
}