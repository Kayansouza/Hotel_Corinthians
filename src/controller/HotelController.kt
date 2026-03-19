package controller


class ReservaService{
    fun calcularValorTotal(valorDiaria: Double, dias: Int): Double {
        if (dias <= 0) return 0.0
        return valorDiaria * dias

    }
}

class HotelController( private val reservaService: ReservaService = ReservaService()){
    fun iniciarSistema() {
        val valorDiaria = 380.00
        println("--- Reserva de Quartos ---")
        print("Quantas diárias você deseja reservar? ")
        val dias = readln().toIntOrNull() ?: 0

        val total = reservaService.calcularValorTotal(valorDiaria, dias)
        
        if (total > 0) {
            println("O valor total para $dias diárias é: R$ $total")
        } else {
            println("Quantidade de diárias inválida.")
        }
    }
}