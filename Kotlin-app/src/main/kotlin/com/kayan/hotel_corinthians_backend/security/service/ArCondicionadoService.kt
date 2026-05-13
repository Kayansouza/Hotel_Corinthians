package com.kayan.hotel_corinthians_backend.security.service

class ArCondicionadoService {
    fun calcular(valor: Double, qtd: Int, perc: Double, min: Int, frete: Double): Double {
        val bruto = valor * qtd
        val desc = if (qtd >= min) bruto * (perc / 100) else 0.0
        return bruto - desc + frete
    }
}