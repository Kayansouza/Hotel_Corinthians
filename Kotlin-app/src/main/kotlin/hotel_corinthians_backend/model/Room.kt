package com.kayan.hotel_corinthians_backend.model

import jakarta.persistence.*
import java.math.BigDecimal // Necessário para o DPE (Requisito 2.1)

@Entity
class Room(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val baseRate: BigDecimal = BigDecimal.ZERO // Esta linha mata o erro do PricingService!
)