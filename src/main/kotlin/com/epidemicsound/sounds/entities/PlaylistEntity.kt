package com.epidemicsound.sounds.entities

import com.epidemicsound.openapi.models.Playlist
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

@Entity
@Table(name = "playlists")
class PlaylistEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "title")
    var title: String,

    @JoinTable(
        name = "playlist_sounds",
        joinColumns = [JoinColumn(name = "playlist_id")],
    )
    @OneToMany(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
        fetch = FetchType.LAZY,
        targetEntity = SoundEntity::class
    )
    var sounds: List<SoundEntity>
) {
    fun toModel() = Playlist(
        this.id.toString(),
        this.title,
        this.sounds.map { it.toModel() }
    )
}
