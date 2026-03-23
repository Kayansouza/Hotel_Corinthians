package controller

import security.service.EventoService

class EventoController {
    private val service = EventoService()

    fun gerenciarEvento(nomeUsuario: String) {
        println("\n--- [Eventos] Planejamento ---")
        print("Quantidade de convidados: ")
        val convidados = readln().toIntOrNull() ?: -1

        if (convidados <= 0 || convidados > 350) {
            println("❌ Número de convidados inválido.")
            return
        }

        // Lógica de Auditório
        val (auditorio, extras) = if (convidados <= 220) {
            val cadeirasExtras = if (convidados > 150) convidados - 150 else 0
            "Laranja" to cadeirasExtras
        } else {
            "Colorado" to 0
        }
        println("Auditório selecionado: $auditorio" + if (extras > 0) " ($extras cadeiras adicionais)" else "")

        // Agenda
        print("Dia da semana: ")
        val dia = readln().lowercase().trim()
        print("Hora inicial (7-23): ")
        val horaIni = readln().toIntOrNull() ?: -1
        print("Duração (horas): ")
        val duracao = readln().toIntOrNull() ?: 1

        val limite = if (dia == "sabado" || dia == "domingo") 15 else 23
        if (horaIni < 7 || (horaIni + duracao) > limite) {
            println("❌ Auditório indisponível. Horário permitido: 07h às ${limite}h")
            return
        }

        print("Empresa: ")
        val empresa = readln()

        // Cálculos via Service
        val totalGarcons = service.calcularGarcons(convidados, duracao)
        val (cafe, agua, salgados) = service.calcularBuffet(convidados)
        val totalGeral = service.calcularCusto(totalGarcons, duracao, cafe, agua, salgados)

        println("\n--- RESUMO DO EVENTO ---")
        println("Empresa: $empresa | Total Geral: R$ ${"%.2f".format(totalGeral)}")
        print("\n$nomeUsuario, confirma a reserva? (S/N): ")
        if (readln().uppercase() == "S") println("✅ Reserva efetuada!")
    }
}