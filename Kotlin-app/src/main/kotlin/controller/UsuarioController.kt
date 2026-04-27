package controller

import model.Cliente
import security.service.Funcionario

open class UsuarioController {

    fun validarAno(entrada: String): Int? {
        val ano = entrada.toIntOrNull()
        return if (ano != null && ano in 1900..2026) ano else null
    }

    fun emailValido(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(regex)
    }

    fun emailDisponivel(email: String, lista: List<Any>): Boolean {
        return lista.none { item ->
            when (item) {
                is Cliente -> item.email.equals(email, ignoreCase = true)
                is Funcionario -> item.email.equals(email, ignoreCase = true)
                else -> false
            }
        }
    }

    open fun processarCadastro(email: String, ano: Int?, lista: List<Any>): Boolean {
        val emailTemFormatoCerto = emailValido(email)
        val emailNaoEstaSendoUsado = emailDisponivel(email, lista)
        val anoEstaNoLimite = ano != null && ano in 1900..2026

        return emailTemFormatoCerto && emailNaoEstaSendoUsado && anoEstaNoLimite
    }
}