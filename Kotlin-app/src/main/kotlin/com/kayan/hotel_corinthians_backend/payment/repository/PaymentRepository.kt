package com.kayan.hotel_corinthians_backend.payment.repository

import com.kayan.hotel_corinthians_backend.payment.model.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {

    fun findByReservationId(reservationId: Long): List<Payment>
}