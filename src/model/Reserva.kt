import java.time.LocalDateTime
import java.time.Duration

// 1. Representação do Cliente e Reserva
data class Reserva(
    val nomeCliente: String,
    val emailCliente: String,
    var estaAtiva: Boolean = false,
    var dataCheckIn: LocalDateTime? = null,
    var dataCheckOut: LocalDateTime? = null
)

class SistemaHotel {

    // Simulação de uma base de dados (Lista de reservas)
    val reservas = mutableListOf<Reserva>()

    // 2. Função de Check-in
    fun realizarCheckIn(email: String) {
        val reserva = reservas.find { it.emailCliente == email }

        if (reserva != null && !reserva.estaAtiva) {
            reserva.estaAtiva = true
            reserva.dataCheckIn = LocalDateTime.now()
            println("✅ Check-in realizado para: ${reserva.nomeCliente}")
        } else {
            println("❌ Erro: Reserva não encontrada ou cliente já está no hotel.")
        }
    }

    // 3. Função de Check-out
    fun realizarCheckOut(email: String) {
        val reserva = reservas.find { it.emailCliente == email }

        if (reserva != null && reserva.estaAtiva) {
            reserva.estaAtiva = false
            reserva.dataCheckOut = LocalDateTime.now()

            // Lógica extra: Calcular tempo de estadia
            val estadia = Duration.between(reserva.dataCheckIn, reserva.dataCheckOut).toHours()

            println("🏨 Check-out de ${reserva.nomeCliente} concluído. Estadia: $estadia horas.")

            // Aqui chamamos a função de e-mail que aprendemos antes!
            enviarEmailFinalizacao(reserva.emailCliente)
        } else {
            println("❌ Erro: Não é possível fazer check-out de um cliente que não entrou.")
        }
    }

    fun enviarEmailFinalizacao(email: String) {
        println("📧 Enviando notificação de conclusão para: $email")
    }
}

// --- Testando o programa ---
