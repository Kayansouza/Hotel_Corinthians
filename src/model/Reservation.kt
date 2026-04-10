package model

import java.time.LocalDateTime

data class Reservation(
    val id: Long,
    val customerName: String,
    val customerEmail: String,
    var isActive: Boolean = false,
    var checkIn: LocalDateTime? = null,
    var checkOut: LocalDateTime? = null
)