package controller

import security.service.HospedeSerivce
import security.service.ReservationService

class HotelController(
    private val reservationService: ReservationService = ReservationService(),
    private val hospedeService: HospedeSerivce = HospedeSerivce()
) {

    fun iniciarSistema() {
        println("\n--- 🏨 Sistema de Reservas VIP ---")


        print("Digite o e-mail do hóspede: ")
        val email = readln().trim()
        val hospede = hospedeService.buscarHospedePorEmail(email)

        if (hospede == null) {
            println("❌ Erro: Hóspede não encontrado! Faça o cadastro primeiro.")
            return
        }


        val valorDiaria = 380.00
        print("Quantas diárias deseja reservar para ${hospede.nome}? ")
        val dias = readln().toIntOrNull() ?: 0

        val total = reservationService.calculateTotalPrice(valorDiaria, dias)

        if (total > 0) {

            val registro = "Reserva de $dias dias - Total: R$ $total"
            hospede.historico.add(registro)

            println("\n✅ Reserva confirmada!")
            println("Hóspede: ${hospede.nome}")
            println("Valor total: R$ ${"%.2f".format(total)}")
            println("📜 Histórico do cliente atualizado.")
        } else {
            println("❌ Quantidade de diárias inválida.")
        }
    }
}