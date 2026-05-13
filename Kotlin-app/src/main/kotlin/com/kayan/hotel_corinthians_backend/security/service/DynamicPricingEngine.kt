package com.kayan.hotel_corinthians_backend.security.service

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DynamicPricingEngine {

    // Requisito 2.1: Cálculo baseado em variáveis de demanda e ocupação
    fun calculate(
        baseRate: BigDecimal,
        occupancyRate: Double,
        isEventProximity: Boolean
    ): BigDecimal {


        val occupancyModifier = if (occupancyRate > 0.8) 0.20 else 0.0


        val demandFactor = if (isEventProximity) 0.15 else 0.0

        // Fórmula do Spec: Final Price = Base Rate * (1 + Demand Factor + Occupancy Modifier)
        val totalMultiplier = 1.0 + demandFactor + occupancyModifier

        return baseRate.multiply(BigDecimal.valueOf(totalMultiplier))
    }
}