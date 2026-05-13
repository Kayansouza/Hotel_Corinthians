package com.kayan.hotel_corinthians_backend.security.service

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestTemplate
import org.springframework.cache.annotation.Cacheable
import java.math.BigDecimal
import java.math.RoundingMode

// Se CurrencyResponse estiver em outro arquivo, verifique o import
// import com.kayan.hotel_corinthians_backend.dto.CurrencyResponse

@Service
class CurrencyService(
    @Value("\${currency.api.key:test}") private val apiKey: String,
    private val restTemplate: RestTemplate
) {
    private val apiUrl = "https://v6.exchangerate-api.com/v6/"

    @Cacheable(value = ["currencyCache"], key = "#fromCurrency + '-' + #toCurrency")
    fun convert(amount: BigDecimal, fromCurrency: String, toCurrency: String): BigDecimal {
        if (fromCurrency == toCurrency) return amount

        val url = "$apiUrl$apiKey/latest/$fromCurrency"

        val response = restTemplate.getForObject(url, CurrencyResponse::class.java)
            ?: throw IllegalStateException("Falha ao obter taxas de câmbio")

        val rateTo = response.rates[toCurrency]?.toBigDecimal()
            ?: throw IllegalArgumentException("Moeda de destino ($toCurrency) não suportada")

        return amount.multiply(rateTo)
            .setScale(4, RoundingMode.HALF_UP)
    }
}