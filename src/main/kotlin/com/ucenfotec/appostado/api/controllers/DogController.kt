package com.ucenfotec.appostado.api.controllers

import com.ucenfotec.appostado.core.application.common.interfaces.IDogService
import com.ucenfotec.appostado.core.application.dtos.dog.DogDTO
import com.ucenfotec.appostado.core.application.dtos.dog.DogDetailDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture

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
        return ResponseEntity.ok(createdDog);
    }

    @PutMapping("{dogId}")
    fun updateDog(@PathVariable dogId: String, @RequestBody dog: DogDetailDTO): ResponseEntity<Any> {
        val updatedDog = dogService.updateDog(dogId, dog);
        return ResponseEntity.ok(updatedDog);
    }

    @DeleteMapping("{dogId}")
    fun deleteDog(@PathVariable dogId: String): ResponseEntity<Any> {
        val deletedDog = dogService.deleteDog(dogId);
        return ResponseEntity.ok(deletedDog);
    }

}