package security.service

class EventoService {

    fun validarEvento(escolha: String): String {
        return when (escolha) {
            "1" -> "Evento selecionado: Aniversário do Corinthians"
            "2" -> "Evento selecionado: Grande Evento"
            else -> "Evento inválido! Continuando sem evento especial."
        }
    }

    fun obterMarcasBebida(opcao: String): List<String> {
        return when (opcao) {

            "1" -> listOf(
                "Bavária",
                "Budweiser",
                "Heineken",
                "Itaipava",
                "Beck's",
                "Corona",
                "Skol"
            )

            "2" -> listOf(
                "Coca-Cola",
                "Coca-Cola Zero",
                "Guaraná",
                "Fanta Laranja",
                "Fanta Uva",
                "Sprite"
            )

            "3" -> listOf(
                "Água Mineral",
                "Água com Gás"
            )

            else -> emptyList()
        }
    }

    fun filtrarOpcoesKids(opcao: String): Boolean {
        return opcao == "2" || opcao == "3"
    }
}

// ---------------------------
// MAIN
// ---------------------------

fun main() {

    val eventoService = EventoService()

    println("---- EVENTOS DO HOTEL ----")
    println("1 - Aniversário do Corinthians")
    println("2 - Grande Evento")

    print("Escolha o evento: ")
    val escolhaEvento = readln()

    println(eventoService.validarEvento(escolhaEvento))

    println("\n---- ESCOLHA A BEBIDA ----")
    println("1 - Cerveja")
    println("2 - Refrigerante")
    println("3 - Água")

    print("Digite a opção: ")
    val opcaoBebida = readln()

    val marcas = eventoService.obterMarcasBebida(opcaoBebida)

    if (marcas.isEmpty()) {

        println("Opção inválida!")

    } else {

        println("\nMarcas disponíveis:")
        println(marcas.joinToString(", "))

        val kids = eventoService.filtrarOpcoesKids(opcaoBebida)

        if (kids) {
            println("✅ Essa bebida pode ser consumida por crianças.")
        } else {
            println("❌ Bebida alcoólica não permitida para crianças.")
        }
    }
}
