package com.epidemicsound.soundrecommender.repositories

import com.epidemicsound.soundrecommender.entities.CreditEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository : JpaRepository<CreditEntity, Long>
