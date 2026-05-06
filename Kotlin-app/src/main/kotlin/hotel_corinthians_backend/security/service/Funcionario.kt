package com.kayan.hotel_corinthians_backend.security.service


data class Funcionario(
    val nome: String,
    val anoDeNascimento: Int, // Adicionamos esse campo!
    val cpf: String,
    val email: String,
    val cargo: String,
    val senhaHash: String
)
