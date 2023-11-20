package com.epidemicsound.sounds.e2e

import com.epidemicsound.openapi.client.models.Credit
import com.epidemicsound.openapi.client.models.NewPlaylist
import com.epidemicsound.openapi.client.models.NewPlaylistRequest
import com.epidemicsound.openapi.client.models.NewSound
import com.epidemicsound.openapi.client.models.NewSoundsRequest
import com.epidemicsound.openapi.client.models.SoundsResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class SoundsTest {
    @Autowired
    lateinit var mvc: MockMvc

    private val mapper = jacksonObjectMapper()

    @Test
    fun createSound() {
        val soundsPayload =
            NewSoundsRequest(
                listOf(
                    NewSound(
                        "Forgot About Dre",
                        120,
                        listOf("hip-hop"),
                        300,
                        listOf(
                            Credit("Dr Dre", Credit.Role.pRODUCER),
                            Credit("Dr Dre", Credit.Role.vOCALIST),
                            Credit("Eminem", Credit.Role.vOCALIST),
                        ),
                    ),
                ),
            )

        mvc.perform(
            post("/admin/sounds")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(soundsPayload)),
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data").isNotEmpty)
            .andExpect(jsonPath("$.data[0].id").exists())
            .andExpect(jsonPath("$.data[0].title").exists())
            .andExpect(jsonPath("$.data[0].title").value(soundsPayload.data[0].title))
            .andExpect(jsonPath("$.data[0].bpm").value(soundsPayload.data[0].bpm))
            .andExpect(jsonPath("$.data[0].genres").isArray)
            .andExpect(jsonPath("$.data[0].genres[0]").value(soundsPayload.data[0].genres[0]))
            .andExpect(jsonPath("$.data[0].credits").isArray)
            .andExpect(jsonPath("$.data[0].credits[0].name").value(soundsPayload.data[0].credits[0].name))
            .andExpect(jsonPath("$.data[0].credits[0].role").value(soundsPayload.data[0].credits[0].role.value))
            .andExpect(jsonPath("$.data[0].credits[1].name").value(soundsPayload.data[0].credits[1].name))
            .andExpect(jsonPath("$.data[0].credits[1].role").value(soundsPayload.data[0].credits[1].role.value))

        val soundsResult =
            mvc.perform(get("/sounds"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isNotEmpty)
                .andExpect(jsonPath("$.data[0].title").isString)
                .andExpect(jsonPath("$.data[0].title").isNotEmpty)
                .andReturn()

        val sound = mapper.readValue(soundsResult.response.contentAsString, SoundsResponse::class.java)

        val playlistPayload =
            NewPlaylistRequest(
                listOf(
                    NewPlaylist(
                        "Hip-Hop",
                        sound.data.map { it.id },
                    ),
                ),
            )

        mvc.perform(
            post("/playlists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(playlistPayload)),
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data").isNotEmpty)
            .andExpect(jsonPath("$.data[0].title").isString)
            .andExpect(jsonPath("$.data[0].title").isNotEmpty)
    }
}
