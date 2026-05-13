package com.kayan.hotel_corinthians_backend.security.service


import java.security.MessageDigest

class PasswordService {

    fun lerSenha(): String {

        val console = System.console()

        if (console != null) {

            val senhaChar = console.readPassword("Digite a senha: ")
            return String(senhaChar)

        } else {

            print("Digite a senha: ")
            return readln()

        }
    }

    fun criptografarSenha(senha: String): String {

        val md = MessageDigest.getInstance("SHA-256")
        val hash = md.digest(senha.toByteArray())

        return hash.joinToString("") {
            "%02x".format(it)
        }
    }
}
