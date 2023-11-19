package com.epidemicsound.sounds.controllers

import com.epidemicsound.openapi.apis.PlaylistsApi
import com.epidemicsound.openapi.models.NewPlaylistRequest
import com.epidemicsound.openapi.models.Playlist
import com.epidemicsound.openapi.models.PlaylistResponse
import com.epidemicsound.sounds.services.PlaylistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaylistsController : PlaylistsApi {
    @Autowired
    private lateinit var playlistService: PlaylistService

    override fun playlistsPost(newPlaylistRequest: NewPlaylistRequest): ResponseEntity<PlaylistResponse> {
        return ResponseEntity.ok(playlistService.createPlaylist(newPlaylistRequest))
    }
}
