package security.service

import model.Cliente

class AuthService {

    /**
     * Valida o login do cliente.
     * Retorna o objeto Cliente se tudo estiver certo, ou null se falhar.
     */
    fun validarLogin(cpf: String, senha: String, lista: List<Cliente>): Cliente? {

        // 1. Procuramos o cliente (corrigido para 'lista')
        val cliente = lista.find { it.cpf == cpf }

        // 2. Verificamos se ele existe E se a senha coincide
        return if (cliente != null && cliente.senhaHash == senha) {
            cliente // Sucesso!
        } else {
            null // Falha (o Main cuidará da mensagem de erro)
        }
    }
}