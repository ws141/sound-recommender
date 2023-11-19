package com.epidemicsound.sounds.entities

import com.epidemicsound.openapi.models.NewSound
import com.epidemicsound.openapi.models.Sound
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "sounds")
class SoundEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "title")
    var title: String,
    @Column(name = "bpm")
    var bpm: Int,
    @Column(name = "genres")
    var genres: List<String>,
    @Column(name = "duration_in_seconds")
    var durationInSeconds: Int,
) {
    constructor(newSound: NewSound) : this (
        title = newSound.title,
        bpm = newSound.bpm,
        genres = newSound.genres,
        durationInSeconds = newSound.durationInSeconds,
    )

    fun toModel() = Sound(
            this.id.toString(),
            this.title,
            this.bpm,
            this.genres,
            this.durationInSeconds,
        )
}
