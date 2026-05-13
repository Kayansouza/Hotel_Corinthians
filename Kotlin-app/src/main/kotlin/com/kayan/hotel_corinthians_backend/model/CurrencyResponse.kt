package com.kayan.hotel_corinthians_backend.model

data class CurrencyResponse(
    val base: String,
    val rates: Map<String, Double>
)