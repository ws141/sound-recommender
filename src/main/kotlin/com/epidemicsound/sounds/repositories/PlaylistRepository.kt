package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.PlaylistEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaylistRepository : JpaRepository<PlaylistEntity, Long>
