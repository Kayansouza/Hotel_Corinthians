package com.kayan.hotel_corinthians_backend.security.service

class EmailService {

    // Regra: Precisa ter caracteres, um @ e um domínio
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    fun ehValido(email: String): Boolean {
        return email.isNotBlank() && email.matches(emailRegex)
    }

    fun enviarConfirmaçao(email: String) {

        println("Enviando email de confirmação para $email...")
        println("ServiçO: Enviando email de confirmação para $email...")
        println("Email de confirmação enviado para $email!")
}
    }




