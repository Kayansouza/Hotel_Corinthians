package controller

import security.service.Funcionario
import state.HotelState

// Aqui temos apenas a classe do funcionário, sem o UsuarioController duplicado
class FuncionarioController : UsuarioController() {

    fun validarCodigoFuncionario(codigo: String): Boolean {
        return codigo == "BANDO2026"
    }

    fun processarCadastroFuncionario(
        email: String,
        ano: Int?,
        codigo: String
    ): Boolean {
        val dadosBasicosValidos = super.processarCadastro(email, ano, HotelState.listaFuncionarios)
        val codigoValido = validarCodigoFuncionario(codigo)

        return dadosBasicosValidos && codigoValido
    }
}