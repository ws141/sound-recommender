package com.epidemicsound.soundrecommender.repositories

import com.epidemicsound.soundrecommender.entities.SoundEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SoundRepository : JpaRepository<SoundEntity, Long> {
    @Query(
        """
            SELECT DISTINCT s.id, s.title, s.bpm, s.genres, s.duration_in_seconds FROM sounds s
            JOIN sound_credits sc ON sc.sound_id = s.id
            JOIN credits c ON sc.credit_id = c.id
            WHERE genres && CAST(:genres AS VARCHAR[])
            AND c."name" IN :names
        """,
        nativeQuery = true,
    )
    fun findRecommended(
        @Param("genres") genres: Array<String>,
        @Param("names") names: List<String>,
    ): List<SoundEntity>
}
