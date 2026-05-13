package com.kayan.hotel_corinthians_backend.security.service

import com.kayan.hotel_corinthians_backend.state.HotelState
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.kayan.hotel_corinthians_backend.repository.ReservationRepository
import com.kayan.hotel_corinthians_backend.model.Reservation
import com.kayan.hotel_corinthians_backend.dto.ReservationRequest

// AJUSTE: Verifique se estas classes estão em 'security.service' ou apenas 'service'
import com.kayan.hotel_corinthians_backend.security.service.DynamicPricingEngine
import com.kayan.hotel_corinthians_backend.security.service.SmartRoomIoTService

@Service
class ReservationService(
    private val repository: ReservationRepository,
    private val pricingEngine: DynamicPricingEngine,
    private val ioTService: SmartRoomIoTService
) {

    @Transactional
    fun createReservation(request: ReservationRequest): Reservation {

        val isOccupied = repository.existsOverlapping(
            request.roomId, request.checkIn, request.checkOut
        )
        if (isOccupied) throw IllegalStateException("Quarto já reservado para este período.")

        val totalRooms = 100.0
        val occupiedRooms = repository.countTotalActiveReservations()
        val occupancyRate = occupiedRooms / totalRooms

        // AJUSTE CORRETO: Passando a variável diretamente, sem conversões!
        val finalPrice = pricingEngine.calculate(
            baseRate = request.baseRate,
            occupancyRate = occupancyRate,
            isEventProximity = request.isNearEvent
        )

        val reservation = Reservation(
            roomId = request.roomId,
            guestName = request.guestName,
            guestEmail = request.guestEmail,
            checkIn = request.checkIn,
            checkOut = request.checkOut,
            finalPrice = finalPrice,
            currency = request.currency,
            isActive = true
        )

        val savedReservation = repository.save(reservation)

        ioTService.prepareRoom(savedReservation.roomId)

        return savedReservation
    }
}