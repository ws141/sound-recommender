package com.epidemicsound.soundrecommender.repositories

import com.epidemicsound.soundrecommender.entities.PlaylistEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaylistRepository : JpaRepository<PlaylistEntity, Long>
