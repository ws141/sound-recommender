package com.epidemicsound.sounds.services

import com.epidemicsound.openapi.models.NewSoundsRequest
import com.epidemicsound.openapi.models.SoundsResponse
import com.epidemicsound.sounds.entities.Sound
import com.epidemicsound.sounds.repositories.SoundRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SoundService {
    @Autowired
    lateinit var soundRepository: SoundRepository

    fun createSound(newSoundsRequest: NewSoundsRequest): SoundsResponse {
        val sounds =
            newSoundsRequest.data?.map {
                soundRepository.save(Sound(it)).toModel()
            }

        return SoundsResponse(sounds)
    }

    fun getSounds(): SoundsResponse {
        TODO("Not yet implemented")
    }

    fun getRecommended(playlistId: String): SoundsResponse {
        TODO("Not yet implemented")
    }
}
