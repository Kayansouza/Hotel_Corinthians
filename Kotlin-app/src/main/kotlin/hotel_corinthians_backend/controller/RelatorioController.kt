package com.kayan.hotel_corinthians_backend.controller

import com.kayan.hotel_corinthians_backend.model.Client
import com.kayan.hotel_corinthians_backend.state.HotelState
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/reports") // Rota: http://localhost:8080/reports
class RelatorioController {

    @GetMapping("/summary") // Isso faz o botão aparecer no Swagger!
    fun getSummary(): String {
        // Simulando os dados que antes eram passados por parâmetro
        val quartosOcupados = 5 // Exemplo estático ou vindo de um Service
        val totalHospedes = HotelState.clientList.size

        // Retornamos uma String formatada que aparecerá na tela do navegador
        return """
            📊 RELATÓRIO: ${HotelState.hotelName}
            ========================================
            Hóspedes: $totalHospedes | Quartos: $quartosOcupados/20
            TOTAL ACUMULADO: R$ ${"%.2f".format(500.0)}
            ========================================
        """.trimIndent()
    }

    @GetMapping("/list-guests")
    fun listGuests(): List<Client> {
        return HotelState.clientList // Retorna a lista real para o Swagger mostrar
    }
}