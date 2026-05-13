package com.kayan.hotel_corinthians_backend.security.service

// Esta classe mapeia a estrutura do JSON que vem da API
data class CurrencyResponse(
    val rates: Map<String, Double> = emptyMap(),
    val result: String? = null,
    val base_code: String? = null
)