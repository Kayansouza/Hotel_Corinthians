package com.kayan.hotel_corinthians_backend.model

import jakarta.persistence.*

@Entity
class HotelStateEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var totalRevenue: Double = 0.0,
    var occupiedRoomsCount: Int = 0
)