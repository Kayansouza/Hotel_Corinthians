package controller

import security.service.HospedeSerivce

class HospedeController {

    private val hospede = HospedeSerivce()

    fun menuCadastro() {
        println("\n--- 📝 Novo Cadastro ---")
        print("Nome: ")
        val nome = readln()
        print("Email: ")
        val email = readln()
        print("Idade: ")
        val idade = readln().toIntOrNull() ?: 0

        // Chamamos o serviço e guardamos a resposta
        val resultado = hospede.processarCadastro(nome, email, idade)

        // O Controller apenas exibe o que o Service decidiu
        println(resultado)
    }
}