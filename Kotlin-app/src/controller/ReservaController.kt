package controller

class ReservaController {

    fun reservaDeQuartos(nomeUsuario: String, quartos: Array<String>) {
        println("\n---- [Reservas] ----")


        print("Valor da diária: ")
        val valorDiaria = readln().toDoubleOrNull() ?: -1.0
        print("Quantidade de diárias (1-30): ")
        val dias = readln().toIntOrNull() ?: -1

        if (valorDiaria <= 0 || dias !in 1..30) {
            println("❌ Valor Inválido, $nomeUsuario")
            return
        }

        // 2. Lógica de Quarto (Grade 4x5 que o prof pediu)

        exibirGrade(quartos)
        print("Escolha um quarto (1-20): ")
        var n = readln().toIntOrNull() ?: 0

        while (n !in 1..20 || quartos[n-1] != "Livre") {
            println("⚠️ Quarto ocupado ou inválido! Quartos livres:")
            quartos.forEachIndexed { i, s -> if(s == "Livre") print("${i+1} ") }
            print("\nEscolha outro: ")
            n = readln().toIntOrNull() ?: 0
        }


        // (Aqui você chama o Service para calcular o totalFinal)
        println("✅ Quarto $n selecionado.")
        // ... resto da sua lógica de confirmação S/N
    }

    private fun exibirGrade(quartos: Array<String>) {
        println("\n--- MAPA DE QUARTOS ---")
        for (i in 0 until 20) {
            val status = if (quartos[i] == "Livre") "L" else "O"
            print("[$status] ")
            if ((i + 1) % 5 == 0) println()
        }
    }
}