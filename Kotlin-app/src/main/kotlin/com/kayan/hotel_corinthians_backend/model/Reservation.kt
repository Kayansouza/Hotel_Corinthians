package com.kayan.hotel_corinthians_backend.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal // Para lidar com dinheiro com precisão
import java.util.*

@Entity
@Table(name = "reservations")
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val roomId: Long, // Essencial para validar ocupação e evitar double-booking

    @Column(nullable = false)
    val guestName: String,

    val guestEmail: String,

    @Column(nullable = false)
    val checkIn: LocalDateTime,

    @Column(nullable = false)
    val checkOut: LocalDateTime,

    @Column(nullable = false, precision = 10, scale = 2)
    var finalPrice: BigDecimal,

    @Column(nullable = false, length = 3)
    var currency: String = "BRL",

    @Column(nullable = false)
    var isActive: Boolean = true,

    @Version
    val version: Long? = null
)