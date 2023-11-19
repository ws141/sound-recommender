package com.epidemicsound.sounds.repository

import com.epidemicsound.openapi.models.Credit
import com.epidemicsound.openapi.models.NewSound
import com.epidemicsound.sounds.entities.SoundEntity
import com.epidemicsound.sounds.repositories.SoundRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SoundRepositoryTest {
    @Autowired
    lateinit var soundRepository: SoundRepository

    @Test
    fun createSound() {
        val sound =
            SoundEntity(
                NewSound(
                    "Title",
                    123,
                    listOf("pop"),
                    300,
                    listOf(Credit("Beyonce", Credit.Role.vOCALIST)),
                ),
            )

        val saved = soundRepository.save(sound)

        assertEquals(sound.title, saved.title)
        assertEquals(sound.bpm, saved.bpm)
        assertEquals(sound.genres, saved.genres)
        assertEquals(sound.durationInSeconds, saved.durationInSeconds)
        assertEquals(sound.credits, saved.credits)
    }
}
