package com.ucenfotec.appostado.api.controllers

import com.ucenfotec.appostado.core.application.common.interfaces.sport.ISportService
import com.ucenfotec.appostado.core.application.dtos.sport.SportDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/sport")
class SportController @Autowired constructor(
    private val sportService: ISportService
){
    @RequestMapping("{sportId}")
    fun getDogById(@PathVariable sportId : String): ResponseEntity<Any> {
        val sportResultFuture = sportService.getSportById(sportId);
        val sportResult = sportResultFuture.get();
        return ResponseEntity.ok(sportResult);
    }

    @RequestMapping
    fun getSports(): ResponseEntity<Any> {
        val sportsResultFuture = sportService.getSports();
        val sportsResult = sportsResultFuture.get();
        return ResponseEntity.ok(sportsResult);
    }

    @PostMapping
    fun createSport(@RequestBody sport: SportDTO): ResponseEntity<Any> {
        val createdSport = sportService.createSport(sport);
        val createdResult = createdSport.get();
        return ResponseEntity.ok(createdResult);
    }

    @PutMapping("{sportId}")
    fun updateSport(@PathVariable sportId: String, @RequestBody sport: SportDTO): ResponseEntity<Any> {
        val updatedSport = sportService.updateSport(sportId, sport);
        val updatedResult = updatedSport.get();
        return ResponseEntity.ok(updatedResult);
    }

    @DeleteMapping("{sportId}")
    fun deleteSport(@PathVariable sportId: String): ResponseEntity<Any> {
        val deletedSport = sportService.deleteSport(sportId);
        val deletedResult = deletedSport.get();
        return ResponseEntity.ok(deletedResult);
    }
}