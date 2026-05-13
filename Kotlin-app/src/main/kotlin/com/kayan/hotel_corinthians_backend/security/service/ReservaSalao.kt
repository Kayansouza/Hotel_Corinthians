package com.kayan.hotel_corinthians_backend.security.service


import com.kayan.hotel_corinthians_backend.state.HotelState// Importa o estado global do hotel
import com.kayan.hotel_corinthians_backend.model.Client // Importa o modelo de cliente em inglês

fun reservaSalaoFestas() {

    println("\n---- 🎊 Reserva do Salão de Festas ----")

    print("Digite o e-mail do cliente: ")
    val email = readln().trim()

    // Busca o cliente na lista global do HotelState
    val client = HotelState.clientList.find {
        it.email.equals(email, ignoreCase = true)
    }

    if (client == null) {
        println("❌ Cliente não encontrado!")
        return
    }

    print("Digite a data desejada (ex: 25/12/2026): ")
    val desiredDate = readln().trim()

    // Verifica se a data já está ocupada no estado global
    if (HotelState.occupiedSalonDates.contains(desiredDate)) {
        println("❌ Salão já reservado nessa data.")
        return
    }

    // Adiciona a reserva e confirma com o nome do cliente
    HotelState.occupiedSalonDates.add(desiredDate)
    println("✅ Reserva feita com sucesso para ${client.name}")
}