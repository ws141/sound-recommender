package com.epidemicsound.sounds.controllers

import com.epidemicsound.openapi.server.apis.SoundsApi
import com.epidemicsound.openapi.server.models.SoundsResponse
import com.epidemicsound.sounds.services.SoundService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SoundsController : SoundsApi {
    @Autowired
    private lateinit var soundService: SoundService

    override fun soundsGet(): ResponseEntity<SoundsResponse> {
        return ResponseEntity.ok(soundService.getSounds())
    }

    override fun soundsRecommendedGet(playlistId: String): ResponseEntity<SoundsResponse> {
        return ResponseEntity.ok(soundService.getRecommended(playlistId))
    }
}
