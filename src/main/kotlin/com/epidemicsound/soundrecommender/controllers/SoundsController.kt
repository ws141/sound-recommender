package com.epidemicsound.soundrecommender.controllers

import com.epidemicsound.soundrecommender.openapi.server.apis.SoundsApi
import com.epidemicsound.soundrecommender.openapi.server.models.SoundsResponse
import com.epidemicsound.soundrecommender.services.SoundService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SoundsController : SoundsApi {
    @Autowired
    private lateinit var soundService: SoundService

    @Transactional
    override fun soundsGet(): ResponseEntity<SoundsResponse> {
        return ResponseEntity.ok(soundService.getSounds())
    }

    override fun soundsRecommendedGet(playlistId: String): ResponseEntity<SoundsResponse> {
        return ResponseEntity.ok(soundService.getRecommended(playlistId))
    }
}
