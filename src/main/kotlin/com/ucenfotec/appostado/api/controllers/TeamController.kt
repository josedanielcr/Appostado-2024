package com.ucenfotec.appostado.api.controllers

import com.ucenfotec.appostado.core.application.common.interfaces.ITeamService
import com.ucenfotec.appostado.core.application.dtos.team.TeamDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/api/v1/team")
class TeamController @Autowired constructor(
    private val teamService: ITeamService
){
    @RequestMapping("{teamId}")
    fun getTeamById(@PathVariable teamId : String): ResponseEntity<Any> {
        val teamResultFuture = teamService.getTeamById(teamId);
        val teamResult = teamResultFuture.get();
        return ResponseEntity.ok(teamResult);
    }

    @RequestMapping
    fun getTeams(): ResponseEntity<Any> {
        val teamsResultFuture = teamService.getTeams();
        val teamsResult = teamsResultFuture.get();
        return ResponseEntity.ok(teamsResult);
    }

    @RequestMapping("sportId/{sportId}")
    fun getTeamsBySport(@PathVariable sportId : String): ResponseEntity<Any> {
        val teamResultFuture = teamService.getTeamsBySport(sportId);
        val teamResult = teamResultFuture.get();
        return ResponseEntity.ok(teamResult);
    }

    @PostMapping
    fun createTeam(@RequestBody team: TeamDTO): ResponseEntity<Any> {
        val createdTeam = teamService.createTeam(team);
        val createdResult = createdTeam.get();
        return ResponseEntity.ok(createdResult);
    }

    @PutMapping("{teamId}")
    fun updateTeam(@PathVariable teamId: String, @RequestBody team: TeamDTO): ResponseEntity<Any> {
        val updatedTeam = teamService.updateTeam(teamId, team);
        val updatedResult = updatedTeam.get();
        return ResponseEntity.ok(updatedResult);
    }

    @DeleteMapping("{teamId}")
    fun deleteTeam(@PathVariable teamId: String): ResponseEntity<Any> {
        val deletedTeam = teamService.deleteTeam(teamId);
        val deletedResult = deletedTeam.get();
        return ResponseEntity.ok(deletedResult);
    }
}