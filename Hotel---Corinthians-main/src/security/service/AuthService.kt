package security.service
import model.Cliente

/**
* Validar o login do cliente
* Retorna o cliente se o login for bem-sucedido, ou null se falhar
*/

class AuthService {


    fun validarLogin(cpf: String,
                     senha: String,
                     lista: List<Cliente>): Cliente{
        val cliente = lista.find { it.cpf == cpf } // Encontrar o cliente na lista pelo CPF

        // Se o cliente existe e a senha está correta, retorna o cliente
        return (if (cliente != null && cliente.senha == senha) {
            cliente
        } else {
            null // Retorna null para indicar falha no login
        })!!
    }
}

