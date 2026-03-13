package controller

import security.service.ReservaService

class HotelController {

    private val reservaService = ReservaService()

    fun iniciarSistema() {
        val valorDiaria = 350.0
        val dias = 3

        val total = reservaService.calcularValorTotal(valorDiaria, dias)

        println("Total a pagar pela reserva: R$ $total")
    }
}
