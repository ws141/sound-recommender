package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.CreditEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CreditRepository : JpaRepository<CreditEntity, Long>
