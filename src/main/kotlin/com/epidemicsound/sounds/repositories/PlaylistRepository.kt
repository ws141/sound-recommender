package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.PlaylistEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistRepository : JpaRepository<PlaylistEntity, Long>
