package controller

import model.Cliente
import nomeHotel

class RelatorioController {
    fun exibirRelatorio(hospedes: List<Cliente>, quartos: Array<String>, recHosp: Double, recEv: Double, totalEv: Int) {
        val ocupados = quartos.count { it != "Livre" }
        println("\n========================================")
        println("   📊 RELATÓRIO: $nomeHotel")
        println("========================================")
        println("Hóspedes: ${hospedes.size} | Quartos: $ocupados/20")
        println("Eventos Confirmados: $totalEv")
        println("----------------------------------------")
        println("Receita Hospedagens: R$ ${"%.2f".format(recHosp)}")
        println("Receita Eventos:     R$ ${"%.2f".format(recEv)}")
        println("TOTAL ACUMULADO:     R$ ${"%.2f".format(recHosp + recEv)}")
        println("========================================\n")
    }
}