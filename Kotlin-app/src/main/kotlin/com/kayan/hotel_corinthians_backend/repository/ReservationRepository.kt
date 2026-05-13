package com.kayan.hotel_corinthians_backend.repository

import com.kayan.hotel_corinthians_backend.model.Reservation
import com.kayan.hotel_corinthians_backend.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime // Alterado de LocalDate para LocalDateTime para atender ao ISO 8601

@Repository
interface ReservationRepository : JpaRepository<Reservation, Long> {

    fun findByGuestName(guestName: String): List<Reservation>

    // Lógica de sobreposição (Requisito 3.2: Integridade de Dados)
    @Query("""
        SELECT COUNT(r) > 0
        FROM Reservation r
        WHERE r.roomId = :roomId
        AND r.isActive = true
        AND r.checkIn < :checkOut
        AND r.checkOut > :checkIn
    """)
    fun existsOverlapping(
        roomId: Long,
        checkIn: LocalDateTime,
        checkOut: LocalDateTime
    ): Boolean

    // Necessário para o Motor de Preços Dinâmicos (DPE - Requisito 2.1)
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.isActive = true")
    fun countTotalActiveReservations(): Long
}