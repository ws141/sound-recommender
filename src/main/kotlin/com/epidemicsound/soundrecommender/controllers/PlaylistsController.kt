package com.epidemicsound.soundrecommender.controllers

import com.epidemicsound.soundrecommender.openapi.server.apis.PlaylistsApi
import com.epidemicsound.soundrecommender.openapi.server.models.NewPlaylistRequest
import com.epidemicsound.soundrecommender.openapi.server.models.PlaylistResponse
import com.epidemicsound.soundrecommender.openapi.server.models.PlaylistsResponse
import com.epidemicsound.soundrecommender.openapi.server.models.UpdatePlaylistRequest
import com.epidemicsound.soundrecommender.services.PlaylistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaylistsController : PlaylistsApi {
    @Autowired
    private lateinit var playlistService: PlaylistService

    override fun playlistsIdGet(id: Long): ResponseEntity<PlaylistResponse> {
        return ResponseEntity.ok(playlistService.getPlaylist(id))
    }

    override fun playlistsIdPatch(
        id: Long,
        updatePlaylistRequest: UpdatePlaylistRequest,
    ): ResponseEntity<PlaylistResponse> {
        return ResponseEntity.ok(playlistService.updatePlaylist(id, updatePlaylistRequest))
    }

    override fun playlistsPost(newPlaylistRequest: NewPlaylistRequest): ResponseEntity<PlaylistsResponse> {
        return ResponseEntity(playlistService.createPlaylist(newPlaylistRequest), HttpStatus.CREATED)
    }
}
