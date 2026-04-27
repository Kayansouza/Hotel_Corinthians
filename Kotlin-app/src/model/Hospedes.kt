package model

data class Hospede(
    val nome: String,
    val idade: Int,
    val email: String,
    val historico: MutableList<String> = mutableListOf()
)
