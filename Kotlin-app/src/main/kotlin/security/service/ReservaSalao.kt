package security.service

import state.HotelState

fun reservaSalaoFestas() {

    println("\n---- 🎊 Reserva do Salão de Festas ----")

    print("Digite o e-mail do cliente: ")
    val email = readln().trim()

    val cliente = HotelState.listaClientes.find {
        it.email.equals(email, ignoreCase = true)
    }

    if (cliente == null) {
        println("❌ Cliente não encontrado!")
        return
    }

    print("Digite a data desejada (ex: 25/12/2026): ")
    val dataDesejada = readln().trim()

    if (HotelState.datasOcupadasSalao.contains(dataDesejada)) {
        println("❌ Salão já reservado nessa data.")
        return
    }

    HotelState.datasOcupadasSalao.add(dataDesejada)
    println("✅ Reserva feita com sucesso para ${cliente.nome}")
}