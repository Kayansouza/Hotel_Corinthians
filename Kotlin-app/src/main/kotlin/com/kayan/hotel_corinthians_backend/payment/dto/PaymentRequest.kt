package com.kayan.hotel_corinthians_backend.payment.dto

import java.math.BigDecimal

data class PaymentRequest(
    val reservationId: Long,
    val amount: BigDecimal,
    val paymentMethod: String
)