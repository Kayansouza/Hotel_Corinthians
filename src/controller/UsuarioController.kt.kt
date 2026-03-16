package controller

import model.Cliente

open class UsuarioController {

    fun validarAno(entrada: String): Int? {
        val ano = entrada.toIntOrNull()
        return if (ano != null && ano in 1900..2026) ano else null
    }

    fun emailValido(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(regex)
    }

    // Agora o método exige que você envie a lista para ele analisar
    fun emailDisponivel(email: String, lista: List<Cliente>): Boolean {
        return lista.none { it.email.equals(email, ignoreCase = true) }
    }

    // O validador central agora recebe a lista por parâmetro
    fun processarCadastro(email: String, ano: Int?, lista: List<Cliente>): Boolean {
        return emailValido(email) && ano != null && emailDisponivel(email, lista)
    }
}