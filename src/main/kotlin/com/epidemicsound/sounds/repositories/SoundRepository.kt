package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.SoundEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SoundRepository : JpaRepository<SoundEntity, Long>
