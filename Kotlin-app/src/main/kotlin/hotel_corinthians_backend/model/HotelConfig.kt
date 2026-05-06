package com.kayan.hotel_corinthians_backend.model
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class HotelConfig(
    @Id
    val id: Long = 1, // Sempre será 1, pois só existe um hotel
    val nomeHotel: String = "Bando De Loucos",
    var isMatchDay: Boolean = false,
    var specialEventTax: Double = 1.10
)