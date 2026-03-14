package controller

import security.service.EmailService
import java.io.DataInput

class UsuarioController (private val emailService: EmailService) {

    fun processarCadastro (emailInput: String): Boolean {
        if (emailService.ehValido(emailInput)) {
            emailService.enviarConfirmaçao(emailInput)
            return true
        }
        else
            println("Email inválido: $emailInput")
            return false
    }

}