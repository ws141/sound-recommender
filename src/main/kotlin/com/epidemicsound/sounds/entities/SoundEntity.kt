package com.epidemicsound.sounds.entities

import com.epidemicsound.openapi.server.models.NewSound
import com.epidemicsound.openapi.server.models.Sound
import com.vladmihalcea.hibernate.type.array.ListArrayType
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.Type

@Entity(name = "sound")
@Table(name = "sounds")
class SoundEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "title")
    var title: String,
    @Column(name = "bpm")
    var bpm: Int,
    @Type(ListArrayType::class)
    @Column(name = "genres", columnDefinition = "varchar[]")
    var genres: List<String>,
    @Column(name = "duration_in_seconds")
    var durationInSeconds: Int,
    @JoinTable(
        name = "sound_credits",
        joinColumns = [JoinColumn(name = "sound_id")],
        inverseJoinColumns = [JoinColumn(name = "credit_id")],
    )
    @OneToMany(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
        fetch = FetchType.LAZY,
        targetEntity = CreditEntity::class,
    )
    var credits: List<CreditEntity>,
) {
    constructor() : this (
        title = "",
        bpm = 0,
        genres = emptyList(),
        durationInSeconds = 0,
        credits = emptyList(),
    )
    constructor(newSound: NewSound) : this (
        title = newSound.title,
        bpm = newSound.bpm,
        genres = newSound.genres,
        durationInSeconds = newSound.durationInSeconds,
        credits = newSound.credits.map { CreditEntity(it) },
    )

    fun toModel() =
        Sound(
            this.id.toString(),
            this.title,
            this.bpm,
            this.genres,
            this.durationInSeconds,
            this.credits.map { it.toModel() },
        )
}
