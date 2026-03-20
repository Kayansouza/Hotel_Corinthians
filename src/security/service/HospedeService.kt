package security.service

import model.Hospede

class HospedeSerivce {

    private val bancoDeDadosHospede = mutableListOf<Hospede>()

    fun processarCadastro(nome: String, email: String, idade: Int): String {
        val emailExistente = bancoDeDadosHospede.any {
            it.email.equals(email, ignoreCase = true)
        }

        // Uma regra de gratuidade
        val ehGratuito = idade < 6

        if (emailExistente) {
            return "Erro: O email $email já está em uso."
        }

        val novoHospede = Hospede(nome, idade, email)
        bancoDeDadosHospede.add(novoHospede)

        return if (ehGratuito) {
            " Hóspede $nome cadastrado com sucesso GRATUIDADE!"
        } else {
            "Sucesso: Hóspede $nome cadastrado com sucesso."
        }
    }

    fun buscarHospedePorEmail(email: String): Hospede? {
        return bancoDeDadosHospede.find { it.email.equals(email, ignoreCase = true) }
    }
}




