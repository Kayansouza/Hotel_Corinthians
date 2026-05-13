package com.kayan.hotel_corinthians_backend.controller


import com.kayan.hotel_corinthians_backend.dto.ReservationRequest
import com.kayan.hotel_corinthians_backend.security.service.ReservationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/hotel")
class HotelController(private val reservationService: ReservationService) {

    // Rota de teste rápido para ver se a API está de pé
    @GetMapping("/boas-vindas")
    fun boasVindas() = mapOf("mensagem" to "🏨 Bem-vindo ao Sistema do Hotel Corinthians! Backend Online.")

    // Rota adaptada para o Padrão Global (JSON)
    @PostMapping("/reservar")
    fun reservarQuarto(@RequestBody request: ReservationRequest): ResponseEntity<Any> {
        return try {
            // Chama a mágica do DPE e IoT que fizemos no Service
            val reservation = reservationService.createReservation(request)
            ResponseEntity.ok(reservation)
        } catch (e: Exception) {
            // Retorna o erro 400 se o quarto estiver ocupado
            ResponseEntity.badRequest().body(mapOf("erro" to (e.message ?: "Erro desconhecido")))
        }
    }
}