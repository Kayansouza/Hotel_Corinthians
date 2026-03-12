package controller
import security.service.EventoService

fun main() {
    val service = EventoService()

    println(" ---- Eventos Especiais ----")
    print("Aniversario Do Corinthians | Evento Grande ")
    val escolhaEvento = readln()
    println(service.validarEvento(escolhaEvento))

    println("\n ---- Itens do Menu ----")
    println("1 - Cerveja | 2 - Refrigerante | 3 - Água")
    val escolhaItem = readln()

    // Validação para crianças
    if (!service.filtrarOpcoesKids(escolhaItem)) {
        println("⚠️ Atenção: Este item contém álcool e não é servido para crianças!")
    }

    val marcas = service.obterMarcasBebida(escolhaItem)

    if (marcas.isNotEmpty()) {
        println("Opções disponíveis: ${marcas.joinToString(", ")}")
    } else {
        println("Item inválido!")
    }
}