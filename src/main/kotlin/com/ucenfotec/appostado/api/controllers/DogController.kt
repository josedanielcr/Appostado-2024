package com.ucenfotec.appostado.api.controllers

import com.ucenfotec.appostado.core.application.common.interfaces.IDogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/api/v1/dog")
class DogController @Autowired constructor(
    private val dogService: IDogService
){
    @RequestMapping("")
    fun getDog(): ResponseEntity<Any> {
        val dogResult = dogService.getDog()
        return ResponseEntity.ok(dogResult)
    }
}