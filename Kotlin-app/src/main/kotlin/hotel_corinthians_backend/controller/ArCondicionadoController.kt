package com.kayan.hotel_corinthians_backend.controller

import com.kayan.hotel_corinthians_backend.security.service.ArCondicionadoService

class ArCondicionadoController {
    private val service = ArCondicionadoService()

    fun comparar(nomeUsuario: String) {
        var menor = Double.MAX_VALUE
        var melhorEmpresa = ""
        do {
            print("Empresa: "); val emp = readln()
            print("Valor/Aparelho: "); val v = readln().toDoubleOrNull() ?: 0.0
            print("Quantidade: "); val q = readln().toIntOrNull() ?: 0
            print("Desconto (%): "); val d = readln().toDoubleOrNull() ?: 0.0
            print("Mínimo para desconto: "); val m = readln().toIntOrNull() ?: 0
            print("Deslocamento: "); val f = readln().toDoubleOrNull() ?: 0.0

            val total = service.calcular(v, q, d, m, f)
            println("O serviço de $emp custará R$ ${"%.2f".format(total)}")

            if (total < menor) { menor = total; melhorEmpresa = emp }
            print("Deseja informar novos dados, $nomeUsuario? (S/N): ")
        } while (readln().uppercase() == "S")
        println("\n🏆 Melhor orçamento: $melhorEmpresa — R$ ${"%.2f".format(menor)}")
    }
}