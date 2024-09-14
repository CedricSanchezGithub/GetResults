package com.example.getresults

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class GetResultsApplication
    fun main(args: Array<String>) {
        runApplication<GetResultsApplication>(*args)
    }

