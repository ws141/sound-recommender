package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.SoundEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SoundRepository : JpaRepository<SoundEntity, Long> {
    @Query("SELECT s FROM sound s WHERE s.id IN :ids")
    fun findByIds(
        @Param("ids") ids: List<Long>,
    ): List<SoundEntity>
}
