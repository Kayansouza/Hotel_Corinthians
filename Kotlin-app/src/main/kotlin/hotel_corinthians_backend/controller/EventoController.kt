package com.kayan.hotel_corinthians_backend.controller

import kotlin.math.ceil
import kotlin.math.floor

// Como .nomeHotel está no Main.kt, passamos como parâmetro ou acessamos via pacote
class EventoController {

    fun gerenciarEvento(nomeUsuario: String, nomeHotel: String) {
        println("\n--- [Eventos] $nomeHotel ---")

        // 1. CAPACIDADE (Parte A)
        print("Quantidade de convidados: ")
        val convidados = readln().toIntOrNull() ?: -1

        if (convidados <= 0 || convidados > 350) {
            println("❌ Número de convidados inválido.")
            return
        }

        val (auditorio, extras) = if (convidados <= 220) {
            val cadeirasExtras = if (convidados > 150) convidados - 150 else 0
            "Laranja" to cadeirasExtras
        } else {
            "Colorado" to 0
        }
        println("Auditório selecionado: $auditorio" + if (extras > 0) " ($extras cadeiras adicionais)" else "")

        // 2. AGENDA E DISPONIBILIDADE (Parte B)
        print("Dia da semana (ex: segunda): ")
        val dia = readln().lowercase().trim()
        print("Hora inicial (0-23): ")
        val horaIni = readln().toIntOrNull() ?: -1
        print("Duração do evento (horas): ")
        val duracao = readln().toIntOrNull() ?: 1

        val limiteHora = if (dia == "sabado" || dia == "domingo") 15 else 23

        if (horaIni < 7 || horaIni > limiteHora || (horaIni + duracao) > limiteHora) {
            println("❌ Auditório indisponível: O evento deve ocorrer entre 07h e $limiteHora h.")
            return
        }

        print("Nome da empresa: ")
        val empresa = readln()
        println("✅ Auditório reservado para $empresa: $dia às ${horaIni}h.")

        // 3. CÁLCULOS (Garçons e Buffet - Partes C e D)
        val totalGarcons = calcularGarcons(convidados, duracao)
        val custoGarcons = totalGarcons * duracao * 10.50
        val custoBuffet = calcularBuffet(convidados)
        val totalGeral = custoGarcons + custoBuffet

        // 4. RELATÓRIO E CONFIRMAÇÃO (Parte E)
        exibirResumoEvento(empresa, auditorio, totalGarcons, custoGarcons, custoBuffet, totalGeral)

        print("\n$nomeUsuario, confirma a reserva? (S/N): ")
        if (readln().uppercase() == "S") {
            println("✅ Reserva efetuada com sucesso.")
            // Lembre-se de somar na sua variável global de receita no Main!
        } else {
            println("Reserva não efetuada.")
        }
    }

    // --- FUNÇÕES AUXILIARES ---

    private fun calcularGarcons(conv: Int, dur: Int): Int {
        val base = ceil(conv / 12.0).toInt()
        val reforco = floor(dur / 2.0).toInt()
        return base + reforco
    }

    private fun calcularBuffet(convidados: Int): Double {
        val cafeL = convidados * 0.2
        val aguaL = convidados * 0.5
        val salgados = convidados * 7

        val custoCafe = cafeL * 0.80
        val custoAgua = aguaL * 0.40
        val custoSalgados = ceil(salgados / 100.0) * 34.00

        return custoCafe + custoAgua + custoSalgados
    }

    private fun exibirResumoEvento(empresa: String, auditorio: String, garcons: Int, cGarcons: Double, cBuffet: Double, total: Double) {
        println("\n--- 📋 RESUMO DO EVENTO ---")
        println("Empresa: $empresa | Auditório: $auditorio")
        println("Garçons necessários: $garcons | Custo: R$ ${"%.2f".format(cGarcons)}")
        println("Custo Buffet: R$ ${"%.2f".format(cBuffet)}")
        println("TOTAL DO EVENTO: R$ ${"%.2f".format(total)}")
    }
}