package com.kayan.hotel_corinthians_backend.security.service

import com.kayan.hotel_corinthians_backend.model.Client

class HospedeService { // Corrigido de 'Serivce' para 'Service'

    // Agora usamos a lista de 'Client' e não mais 'Hospede'
    private val bancoDeDadosClient = mutableListOf<Client>()

    fun processarCadastro(nome: String, email: String, idade: Int): String {
        // Verifica se o e-mail já existe na lista
        val emailExistente = bancoDeDadosClient.any {
            it.email.equals(email, ignoreCase = true)
        }

        // Regra de negócio: gratuidade para menores de 6 anos
        val ehGratuito = idade < 6

        if (emailExistente) {
            return "Erro: O email $email já está em uso."
        }

        // Criando o novo objeto usando o modelo 'Client'
        // Certifique-se que o seu modelo Client aceita (nome, idade, email) ou ajuste os campos
        val novoClient = Client(name = nome, email = email)
        bancoDeDadosClient.add(novoClient)

        return if (ehGratuito) {
            "Cliente $nome cadastrado com sucesso - POSSUI GRATUIDADE!"
        } else {
            "Sucesso: Hóspede $nome cadastrado com sucesso."
        }
    }

    fun buscarClientPorEmail(email: String): Client? {
        return bancoDeDadosClient.find { it.email.equals(email, ignoreCase = true) }
    }
}