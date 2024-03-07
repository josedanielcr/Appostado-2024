package com.ucenfotec.appostado

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class AppostadoApplication

fun main(args: Array<String>) {
	runApplication<AppostadoApplication>(*args)
}
