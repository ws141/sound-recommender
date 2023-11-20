package com.epidemicsound.soundrecommender.controllers

import com.epidemicsound.soundrecommender.openapi.server.apis.AdminApi
import com.epidemicsound.soundrecommender.openapi.server.models.NewSoundsRequest
import com.epidemicsound.soundrecommender.openapi.server.models.SoundsResponse
import com.epidemicsound.soundrecommender.services.SoundService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController : AdminApi {
    @Autowired
    private lateinit var soundService: SoundService

    override fun adminSoundsPost(newSoundsRequest: NewSoundsRequest): ResponseEntity<SoundsResponse> {
        return ResponseEntity(soundService.createSound(newSoundsRequest), HttpStatus.CREATED)
    }
}
