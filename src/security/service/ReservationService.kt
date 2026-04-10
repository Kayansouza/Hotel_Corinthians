package security.service

import model.Reservation
import java.time.Duration
import java.time.LocalDateTime

class ReservationService {

    private val reservations = mutableListOf<Reservation>()

    fun createReservation(
        id: Long,
        customerName: String,
        customerEmail: String
    ): Reservation {

        if (customerName.isBlank()) {
            throw IllegalArgumentException("Customer name cannot be empty")
        }

        if (customerEmail.isBlank()) {
            throw IllegalArgumentException("Customer email cannot be empty")
        }

        if (reservations.any { it.customerEmail == customerEmail }) {
            throw IllegalStateException("User already has a reservation")
        }

        val reservation = Reservation(
            id = id,
            customerName = customerName,
            customerEmail = customerEmail
        )

        reservations.add(reservation)

        return reservation
    }

    fun checkIn(reservationId: Long) {
        val reservation = findReservationById(reservationId)

        if (reservation.isActive) {
            throw IllegalStateException("Guest already checked in")
        }

        reservation.isActive = true
        reservation.checkIn = LocalDateTime.now()
    }

    fun checkOut(reservationId: Long): Long {
        val reservation = findReservationById(reservationId)

        if (!reservation.isActive) {
            throw IllegalStateException("Guest is not checked in")
        }

        reservation.isActive = false
        reservation.checkOut = LocalDateTime.now()

        val duration = Duration.between(
            reservation.checkIn!!,
            reservation.checkOut!!
        ).toHours()

        sendCheckoutEmail(reservation.customerEmail)

        return duration
    }

    fun calculateTotalPrice(dailyRate: Double, days: Int): Double {

        if (dailyRate <= 0) {
            throw IllegalArgumentException("Daily rate must be greater than zero")
        }

        // Business rule: maximum stay is 30 days
        if (days <= 0 || days > 30) {
            throw IllegalArgumentException("Days must be between 1 and 30")
        }

        return dailyRate * days
    }

    private fun findReservationById(id: Long): Reservation {
        return reservations.find { it.id == id }
            ?: throw NoSuchElementException("Reservation not found")
    }

    private fun sendCheckoutEmail(email: String) {
        // Simulação (depois vira integração real)
    }
}

