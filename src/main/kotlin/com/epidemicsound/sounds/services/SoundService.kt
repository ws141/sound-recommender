package com.epidemicsound.sounds.services

import com.epidemicsound.openapi.models.NewSoundsRequest
import com.epidemicsound.openapi.models.SoundsResponse
import org.springframework.stereotype.Service

@Service
class SoundService {
    fun createSound(newSoundsRequest: NewSoundsRequest): SoundsResponse {
        TODO("Not Implemented")
    }

    fun getSounds(): SoundsResponse {
        TODO("Not yet implemented")
    }

    fun getRecommended(playlistId: String): SoundsResponse {
        TODO("Not yet implemented")
    }
}
