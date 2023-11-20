package com.epidemicsound.sounds.services

import com.epidemicsound.openapi.server.models.NewSoundsRequest
import com.epidemicsound.openapi.server.models.SoundsResponse
import com.epidemicsound.sounds.entities.SoundEntity
import com.epidemicsound.sounds.repositories.PlaylistRepository
import com.epidemicsound.sounds.repositories.SoundRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SoundService {
    @Autowired
    private lateinit var soundRepository: SoundRepository

    @Autowired
    private lateinit var playlistRepository: PlaylistRepository

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
        val playlist = playlistRepository.findById(playlistId.toLong()).get()

        val distinctGenres = playlist.sounds.flatMap { it.genres }.distinct().toTypedArray()
        val soundIds = playlist.sounds.map { it.id }.toTypedArray()

        val sounds = soundRepository.findRecommended(soundIds, distinctGenres)

        return SoundsResponse(sounds.map { it.toModel() })
    }
}
