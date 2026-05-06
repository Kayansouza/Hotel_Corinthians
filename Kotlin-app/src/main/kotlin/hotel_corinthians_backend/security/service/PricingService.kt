package com.kayan.hotel_corinthians_backend.security.service

import com.kayan.hotel_corinthians_backend.model.Room
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Service
class PricingService {


    fun calculateDynamicRate(room: Room, occupancy: Double, isMatchDay: Boolean): BigDecimal {
        var multiplier = BigDecimal.ONE

        if (isMatchDay) multiplier = multiplier.add(BigDecimal("0.15")) // +15% dia de jogo
        if (occupancy > 0.8) multiplier = multiplier.add(BigDecimal("0.10")) // +10% hotel cheio

        return room.baseRate.multiply(multiplier)
    }


    fun calculateTotal(dailyRate: BigDecimal, checkIn: LocalDate, checkOut: LocalDate, guestType: String): BigDecimal {
        val days = ChronoUnit.DAYS.between(checkIn, checkOut).coerceAtLeast(1)

        val typeFactor = when (guestType.uppercase()) {
            "E" -> BigDecimal("1.35") // Executivo
            "L" -> BigDecimal("1.65") // Luxo
            else -> BigDecimal.ONE
        }

        // (Valor x Dias x Tipo) + 10% de taxa de serviço
        val baseAmount = dailyRate.multiply(BigDecimal.valueOf(days)).multiply(typeFactor)
        return baseAmount.multiply(BigDecimal("1.10"))
    }
}