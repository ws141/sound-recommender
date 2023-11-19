package com.epidemicsound.sounds.e2e

import com.epidemicsound.openapi.client.models.Credit
import com.epidemicsound.openapi.client.models.NewSound
import com.epidemicsound.openapi.client.models.NewSoundsRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class SoundsTest {
    @Autowired
    lateinit var mvc: MockMvc

    private val mapper = ObjectMapper()

    @Test
    fun createSound()  {
        val payload =
            NewSoundsRequest(
                listOf(
                    NewSound(
                        "New Sound",
                        120,
                        listOf("pop"),
                        300,
                        listOf(
                            Credit("Dr Dre", Credit.Role.pRODUCER),
                            Credit("Eminem", Credit.Role.vOCALIST),
                        ),
                    ),
                ),
            )

        mvc.perform(
            post("/admin/sounds")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(payload)),
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data").isNotEmpty)
            .andExpect(jsonPath("$.data[0].id").exists())
            .andExpect(jsonPath("$.data[0].title").value(payload.data[0].title))
            .andExpect(jsonPath("$.data[0].bpm").value(payload.data[0].bpm))
            .andExpect(jsonPath("$.data[0].genres").isArray)
            .andExpect(jsonPath("$.data[0].genres[0]").value(payload.data[0].genres[0]))
            .andExpect(jsonPath("$.data[0].credits").isArray)
            .andExpect(jsonPath("$.data[0].credits[0].name").value(payload.data[0].credits[0].name))
            .andExpect(jsonPath("$.data[0].credits[0].role").value(payload.data[0].credits[0].role.value))
            .andExpect(jsonPath("$.data[0].credits[1].name").value(payload.data[0].credits[1].name))
            .andExpect(jsonPath("$.data[0].credits[1].role").value(payload.data[0].credits[1].role.value))
    }
}
