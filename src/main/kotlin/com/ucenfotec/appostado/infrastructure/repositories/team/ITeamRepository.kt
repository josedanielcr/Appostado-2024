package com.ucenfotec.appostado.infrastructure.repositories.team

import com.ucenfotec.appostado.core.domain.entities.Team
import java.util.concurrent.CompletableFuture

interface ITeamRepository {
    fun getTeamById(teamId : String): CompletableFuture<Team>
    fun getTeams(): CompletableFuture<List<Team>>
    fun getTeamsBySport(sportId : String): CompletableFuture<List<Team>>
    fun createTeam(team: Team): CompletableFuture<Team>
    fun updateTeam(teamId: String, team: Team): CompletableFuture<Team>
    fun deleteTeam(teamId: String): CompletableFuture<Boolean>
}