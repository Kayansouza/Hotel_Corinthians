package model

 data class Reserva (
     val cliente: Cliente,
    val quarto: Quarto,
    val dataEntrada: String,
    val dataSaida: String
)