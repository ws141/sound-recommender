package com.epidemicsound.soundrecommender.services

import com.epidemicsound.soundrecommender.entities.SoundEntity
import com.epidemicsound.soundrecommender.openapi.server.models.NewSoundsRequest
import com.epidemicsound.soundrecommender.openapi.server.models.SoundsResponse
import com.epidemicsound.soundrecommender.repositories.PlaylistRepository
import com.epidemicsound.soundrecommender.repositories.SoundRepository
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
        val credits = playlist.sounds.flatMap { it.credits }.distinct()
        val names = credits.map { it.name }.distinct()

        val sounds = soundRepository.findRecommended(distinctGenres, names)

        return SoundsResponse(sounds.map { it.toModel() })
    }
}
