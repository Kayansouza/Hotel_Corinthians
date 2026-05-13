package com.kayan.hotel_corinthians_backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.kayan.hotel_corinthians_backend.model.Reservation // Importe o modelo aqui

@Repository
// Agora o JpaRepository usa a referência correta
interface HotelStateRepository : JpaRepository<Reservation, Long> {

}