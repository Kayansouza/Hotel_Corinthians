package com.kayan.hotel_corinthians_backend.controller

import com.kayan.hotel_corinthians_backend.dto.ReservationRequest
import com.kayan.hotel_corinthians_backend.model.Reservation
import com.kayan.hotel_corinthians_backend.security.service.ReservationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reservations")
class ReservationController(
    private val service: ReservationService
) {

    // Rota que o Postman vai acessar para criar a reserva
    @PostMapping("/book")
    fun createReservation(@RequestBody request: ReservationRequest): ResponseEntity<Any> {
        return try {
            // A "mágica" acontece aqui (validação, preço dinâmico e IoT)
            val reservation = service.createReservation(request)
            ResponseEntity.ok(reservation)

        } catch (e: IllegalStateException) {
            // Se cair aqui, é porque o quarto já está ocupado (Double-booking evitado!)
            ResponseEntity.badRequest().body(mapOf("erro" to e.message))

        } catch (e: Exception) {
            // Qualquer outro erro inesperado
            ResponseEntity.internalServerError().body(mapOf("erro" to "Falha interna no servidor."))
        }
    }
}