package com.epidemicsound.soundrecommender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SoundRecommenderApplication

fun main(args: Array<String>) {
    runApplication<SoundRecommenderApplication>(*args)
}
