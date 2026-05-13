package com.kayan.hotel_corinthians_backend.security.service

import com.kayan.hotel_corinthians_backend.model.Client // Importação profissional
import com.kayan.hotel_corinthians_backend.state.HotelState

class AuthService {
    fun authenticate(email: String): Client? {
        // Busca na lista de clientes usando o nome atualizado
        return HotelState.clientList.find { it.email.equals(email, ignoreCase = true) }
    }
}