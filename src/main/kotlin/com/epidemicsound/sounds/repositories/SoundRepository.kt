package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.SoundEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SoundRepository : JpaRepository<SoundEntity, Long>
