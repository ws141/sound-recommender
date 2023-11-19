package com.epidemicsound.sounds.controllers

import com.epidemicsound.openapi.apis.AdminApi
import com.epidemicsound.openapi.models.NewSoundsRequest
import com.epidemicsound.openapi.models.SoundsResponse
import com.epidemicsound.sounds.services.SoundService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController : AdminApi {
    @Autowired
    private lateinit var soundService: SoundService

    override fun adminSoundsPost(newSoundsRequest: NewSoundsRequest): ResponseEntity<SoundsResponse> {
        return ResponseEntity.ok(soundService.createSound(newSoundsRequest))
    }
}