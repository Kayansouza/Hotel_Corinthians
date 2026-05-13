package com.kayan.hotel_corinthians_backend.state // REMOVIDO o .HotelState do final

import com.kayan.hotel_corinthians_backend.model.Client
import com.kayan.hotel_corinthians_backend.model.Employee

object HotelState {
    const val hotelName: String = "Hotel Corinthians"
    val clientList = mutableListOf<Client>()
    val employeeList = mutableListOf<Employee>()

    // Agora o sistema vai reconhecer esta nova lista corretamente
    val occupiedSalonDates = mutableSetOf<String>()
}