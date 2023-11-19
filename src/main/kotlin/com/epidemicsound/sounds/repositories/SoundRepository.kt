package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.Sound
import org.springframework.data.jpa.repository.JpaRepository

interface SoundRepository : JpaRepository<Sound, Long>
