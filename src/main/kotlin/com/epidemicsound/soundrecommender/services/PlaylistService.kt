package com.epidemicsound.soundrecommender.services

import com.epidemicsound.soundrecommender.entities.PlaylistEntity
import com.epidemicsound.soundrecommender.openapi.server.models.NewPlaylistRequest
import com.epidemicsound.soundrecommender.openapi.server.models.PlaylistResponse
import com.epidemicsound.soundrecommender.openapi.server.models.PlaylistsResponse
import com.epidemicsound.soundrecommender.openapi.server.models.UpdatePlaylistRequest
import com.epidemicsound.soundrecommender.repositories.PlaylistRepository
import com.epidemicsound.soundrecommender.repositories.SoundRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaylistService {
    @Autowired
    private lateinit var playlistRepository: PlaylistRepository

    @Autowired
    private lateinit var soundRepository: SoundRepository

    @Transactional
    fun createPlaylist(newPlaylistRequest: NewPlaylistRequest): PlaylistsResponse {
        val playlists =
            newPlaylistRequest.data.map { playlist ->
                val ids = playlist.sounds.map { it.toLong() }
                val sounds = soundRepository.findAllById(ids)
                val savedPlaylist = playlistRepository.save(PlaylistEntity(title = playlist.title, sounds = sounds))

                savedPlaylist.toModel()
            }

        return PlaylistsResponse(playlists)
    }

    @Transactional
    fun updatePlaylist(
        id: Long,
        updatePlaylistRequest: UpdatePlaylistRequest,
    ): PlaylistResponse {
        val playlist = playlistRepository.findById(id).get()

        playlist.title = updatePlaylistRequest.title ?: playlist.title
        if (updatePlaylistRequest.sounds != null) {
            val ids = updatePlaylistRequest.sounds.map { it.toLong() }
            playlist.sounds = soundRepository.findAllById(ids)
        }

        val updated = playlistRepository.save(playlist)
        return PlaylistResponse(updated.toModel())
    }

    fun getPlaylist(id: Long): PlaylistResponse {
        val playlist = playlistRepository.findById(id).get()
        return PlaylistResponse(playlist.toModel())
    }
}
