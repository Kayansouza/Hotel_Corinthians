package com.kayan.hotel_corinthians_backend.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class ReservationRequest(
    val roomId: Long,
    val guestName: String,
    val guestEmail: String,
    val checkIn: LocalDateTime,  // Padrão ISO 8601
    val checkOut: LocalDateTime,
    val baseRate: BigDecimal,    // Valor base para o cálculo do DPE
    val currency: String = "BRL", // Padrão ISO 4217
    val isNearEvent: Boolean = false // Para o fator de proximidade de eventos
)