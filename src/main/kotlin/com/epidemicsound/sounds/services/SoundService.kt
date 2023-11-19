package com.epidemicsound.sounds.services

import com.epidemicsound.openapi.models.NewSoundsRequest
import com.epidemicsound.openapi.models.SoundsResponse
import com.epidemicsound.sounds.entities.SoundEntity
import com.epidemicsound.sounds.repositories.SoundRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SoundService {
    @Autowired
    lateinit var soundRepository: SoundRepository

    @Transactional
    fun createSound(newSoundsRequest: NewSoundsRequest): SoundsResponse {
        val sounds =
            newSoundsRequest.data.map {
                soundRepository.save(SoundEntity(it)).toModel()
            }

        return SoundsResponse(sounds)
    }

    fun getSounds(): SoundsResponse {
        val sounds = soundRepository.findAll().map { it.toModel() }
        return SoundsResponse(sounds)
    }

    fun getRecommended(playlistId: String): SoundsResponse {
        TODO("Not yet implemented")
    }
}
