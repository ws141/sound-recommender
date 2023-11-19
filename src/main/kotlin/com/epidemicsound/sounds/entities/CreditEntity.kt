package com.epidemicsound.sounds.entities

import com.epidemicsound.openapi.server.models.Credit
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "credits")
class CreditEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name")
    var name: String,
    @Column(name = "role")
    var role: String,
) {
    constructor(credit: Credit) : this(
        name = credit.name,
        role = credit.role.name,
    )

    fun toModel() =
        Credit(
            name = this.name,
            role = Credit.Role.valueOf(this.role),
        )
}
