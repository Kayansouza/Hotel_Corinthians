package state

import model.Cliente
import model.Funcionario

object HotelState {

    val nomeHotel = "Bando De Loucos"

    val listaClientes = mutableListOf<Cliente>()
    val listaFuncionarios = mutableListOf<Funcionario>()

    val quartos = Array(20) { "Livre" }
    val datasOcupadasSalao = mutableListOf<String>()

    var receitaHospedagem = 0.0
    var receitaEventos = 0.0
    var totalEventosConfirmados = 0
}