package com.ucenfotec.appostado.api.controllers

import com.ucenfotec.appostado.core.application.common.interfaces.dog.IDogService
import com.ucenfotec.appostado.core.application.dtos.dog.DogDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/dog")
class DogController @Autowired constructor(
    private val dogService: IDogService
){
    @RequestMapping("{dogId}")
    fun getDogById(@PathVariable dogId : String): ResponseEntity<Any> {
        val dogResultFuture = dogService.getDogById(dogId);
        val dogResult = dogResultFuture.get();
        return ResponseEntity.ok(dogResult);
    }

    @RequestMapping
    fun getDogs(): ResponseEntity<Any> {
        val dogsResultFuture = dogService.getDogs();
        val dogsResult = dogsResultFuture.get();
        return ResponseEntity.ok(dogsResult);
    }

    @PostMapping
    fun createDog(@RequestBody dog: DogDTO): ResponseEntity<Any> {
        val createdDog = dogService.createDog(dog);
        val createdResult = createdDog.get();
        return ResponseEntity.ok(createdResult);
    }

    @PutMapping("{dogId}")
    fun updateDog(@PathVariable dogId: String, @RequestBody dog: DogDTO): ResponseEntity<Any> {
        val updatedDog = dogService.updateDog(dogId, dog);
        val updatedResult = updatedDog.get();
        return ResponseEntity.ok(updatedResult);
    }

    @DeleteMapping("{dogId}")
    fun deleteDog(@PathVariable dogId: String): ResponseEntity<Any> {
        val deletedDog = dogService.deleteDog(dogId);
        val deletedResult = deletedDog.get();
        return ResponseEntity.ok(deletedResult);
    }
}