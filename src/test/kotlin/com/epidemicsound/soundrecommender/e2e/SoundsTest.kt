package com.epidemicsound.soundrecommender.e2e

import com.epidemicsound.soundrecommender.BaseDbTest
import com.epidemicsound.soundrecommender.openapi.client.models.Credit
import com.epidemicsound.soundrecommender.openapi.client.models.NewPlaylist
import com.epidemicsound.soundrecommender.openapi.client.models.NewPlaylistRequest
import com.epidemicsound.soundrecommender.openapi.client.models.NewSound
import com.epidemicsound.soundrecommender.openapi.client.models.NewSoundsRequest
import com.epidemicsound.soundrecommender.openapi.client.models.PlaylistsResponse
import com.epidemicsound.soundrecommender.openapi.client.models.SoundsResponse
import com.epidemicsound.soundrecommender.openapi.client.models.UpdatePlaylistRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class SoundsTest : BaseDbTest() {
    @Autowired
    lateinit var mvc: MockMvc

    private val mapper = jacksonObjectMapper()

    @Test
    fun soundFlow() {
        val soundsPayload =
            NewSoundsRequest(
                listOf(
                    NewSound(
                        "Forgot About Dre",
                        134,
                        listOf("hip-hop"),
                        222,
                        listOf(
                            Credit("Dr Dre", Credit.Role.pRODUCER),
                            Credit("Dr Dre", Credit.Role.vOCALIST),
                            Credit("Eminem", Credit.Role.vOCALIST),
                        ),
                    ),
                    NewSound(
                        "Still D.R.E.",
                        93,
                        listOf("hip-hop"),
                        271,
                        listOf(
                            Credit("Dr Dre", Credit.Role.pRODUCER),
                            Credit("Dr Dre", Credit.Role.vOCALIST),
                            Credit("Snoop Dogg", Credit.Role.vOCALIST),
                        ),
                    ),
                    NewSound(
                        "The Next Episode",
                        93,
                        listOf("hip-hop"),
                        271,
                        listOf(
                            Credit("Dr Dre", Credit.Role.pRODUCER),
                            Credit("Dr Dre", Credit.Role.vOCALIST),
                            Credit("Snoop Dogg", Credit.Role.vOCALIST),
                            Credit("Kurupt", Credit.Role.vOCALIST),
                            Credit("Nate Dogg", Credit.Role.vOCALIST),
                        ),
                    ),
                    NewSound(
                        "Put It On",
                        180,
                        listOf("hip-hop"),
                        218,
                        listOf(
                            Credit("Big L", Credit.Role.vOCALIST),
                        ),
                    ),
                    NewSound(
                        "All My Life",
                        168,
                        listOf("rock"),
                        263,
                        listOf(
                            Credit("Dave Ghrol", Credit.Role.vOCALIST),
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
                        listOf(sound.data[0].id, sound.data[2].id),
                    ),
                ),
            )

        val playlistResult =
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
                .andReturn()

        val playlist = mapper.readValue(playlistResult.response.contentAsString, PlaylistsResponse::class.java)

        mvc.perform(
            get("/sounds/recommended")
                .queryParam("playlistId", playlist.data[0].id),
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data").isNotEmpty)
            .andExpect(jsonPath("$.data.length()").value(3))
            .andExpect(jsonPath("$.data[0].title").isString)
            .andExpect(jsonPath("$.data[0].title").isNotEmpty)
            .andExpect(jsonPath("$.data[0].title").value(sound.data[0].title))

        val playlistUpdatePayload = UpdatePlaylistRequest("Rock", listOf(sound.data[4].id))

        mvc.perform(
            patch("/playlists/" + playlist.data[0].id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(playlistUpdatePayload)),
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isMap)
            .andExpect(jsonPath("$.data.title").value(playlistUpdatePayload.title))
            .andExpect(jsonPath("$.data.sounds[0].title").value(sound.data[4].title))

        mvc.perform(
            get("/playlists/" + playlist.data[0].id),
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isMap)
            .andExpect(jsonPath("$.data").isNotEmpty)
            .andExpect(jsonPath("$.data.title").value(playlistUpdatePayload.title))
            .andExpect(jsonPath("$.data.sounds[0].title").value(sound.data[4].title))
    }
}
