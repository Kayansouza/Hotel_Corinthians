package security.service

import listaClientes
import model.Cliente

// Lista global para o salão (fora das funções)
val datasReservadasSalao = mutableListOf<String>()

fun reservaSalaoFestas() {
    println("\n---- 🎊 Reserva do Salão de Festas ----")

    // 1. Identificação (Reutilizando a lógica de busca por e-mail)
    print("Digite o e-mail do cliente: ")
    val email = readln().trim()
    val cliente = listaClientes.find { it.email.equals(email, ignoreCase = true) }

    if (cliente == null) {
        println("❌ Cliente não encontrado!")
        return
    }

    // 2. Escolha da Data
    print("Digite a data desejada (ex: 25/12/2026): ")
    val dataDesejada = readln().trim()

    // 3. Verificação de Disponibilidade (Igual ao 'if' do quarto, mas na lista de datas)
    if (datasReservadasSalao.contains(dataDesejada)) {
        println("❌ Desculpe, o salão já está reservado para o dia $dataDesejada.")
    } else {
        // 4. Cálculo e Registro
        val valorAluguel = 1200.00 // Preço fixo do salão

        cliente.historico.add("Evento Salão: Dia $dataDesejada - R$ $valorAluguel")

        // Bloqueia a data
        datasReservadasSalao.add(dataDesejada)

        println("✅ Salão reservado com sucesso para ${cliente.nome} no dia $dataDesejada!")
    }
}