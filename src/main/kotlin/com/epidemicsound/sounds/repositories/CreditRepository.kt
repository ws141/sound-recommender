package com.epidemicsound.sounds.repositories

import com.epidemicsound.sounds.entities.CreditEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository : JpaRepository<CreditEntity, Long>
