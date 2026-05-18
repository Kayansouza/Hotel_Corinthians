package com.kayan.hotel_corinthians_backend.payment.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class PaymentResponse(
    val id: Long?,
    val reservationId: Long,
    val amount: BigDecimal,
    val paymentMethod: String,
    val status: String,
    val createdAt: LocalDateTime,
    val paidAt: LocalDateTime?
)