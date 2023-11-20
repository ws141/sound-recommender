package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.SoundEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SoundRepository : JpaRepository<SoundEntity, Long> {
    @Query(
        """
            SELECT * FROM sounds 
            WHERE genres && CAST(:genres AS VARCHAR[])
            AND id NOT IN :soundIds
        """,
        nativeQuery = true,
    )
    fun findRecommended(
        @Param("soundIds") soundIds: Array<Long>,
        @Param("genres") genres: Array<String>,
    ): List<SoundEntity>
}
