package com.epidemicsound.sounds.services

import com.epidemicsound.openapi.server.models.NewPlaylistRequest
import com.epidemicsound.openapi.server.models.PlaylistResponse
import com.epidemicsound.sounds.entities.PlaylistEntity
import com.epidemicsound.sounds.repositories.PlaylistRepository
import com.epidemicsound.sounds.repositories.SoundRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaylistService {
    @Autowired
    lateinit var playlistRepository: PlaylistRepository

    @Autowired
    lateinit var soundRepository: SoundRepository

    @Transactional
    fun createPlaylist(newPlaylistRequest: NewPlaylistRequest): PlaylistResponse {
        val playlists =
            newPlaylistRequest.data.map { playlist ->
                val ids = playlist.sounds.map { it.toLong() }
                val sounds = soundRepository.findByIds(ids)
                val savedPlaylist = playlistRepository.save(PlaylistEntity(title = playlist.title, sounds = sounds))

                savedPlaylist.toModel()
            }

        return PlaylistResponse(playlists)
    }
}
