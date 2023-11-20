package com.epidemicsound.soundrecommender.repository

import com.epidemicsound.soundrecommender.BaseDbTest
import com.epidemicsound.soundrecommender.entities.PlaylistEntity
import com.epidemicsound.soundrecommender.entities.SoundEntity
import com.epidemicsound.soundrecommender.openapi.server.models.Credit
import com.epidemicsound.soundrecommender.openapi.server.models.NewSound
import com.epidemicsound.soundrecommender.repositories.PlaylistRepository
import com.epidemicsound.soundrecommender.repositories.SoundRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PlaylistRepositoryTest : BaseDbTest() {
    @Autowired
    lateinit var soundRepository: SoundRepository

    @Autowired
    lateinit var playlistRepository: PlaylistRepository

    @Test
    @Transactional
    fun createPlaylist() {
        val sound =
            SoundEntity(
                NewSound(
                    "Title",
                    123,
                    listOf("1", "2"),
                    321,
                    listOf(Credit("Beyonce", Credit.Role.vOCALIST)),
                ),
            )

        val saved = soundRepository.save(sound)
        val playlist = PlaylistEntity(title = "New Playlist", sounds = listOf(saved))
        val savedList = playlistRepository.save(playlist)

        assertEquals(playlist.title, savedList.title)
        assertEquals(savedList.sounds[0].title, saved.title)
        assertEquals(savedList.sounds[0].bpm, saved.bpm)
        assertEquals(savedList.sounds[0].genres, saved.genres)
        assertEquals(savedList.sounds[0].durationInSeconds, saved.durationInSeconds)
        assertEquals(savedList.sounds[0].credits, saved.credits)
    }
}
