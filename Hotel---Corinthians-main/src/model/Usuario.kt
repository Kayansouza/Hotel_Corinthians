package model

class Usuario( var nome: String, var cpf: String, var email: String, var telefone: String, var senha: String) {

    // Método Construtor da classe cliente, que é chamado quando um objeto do tipo cliente é criado. Ele recebe os parâmetros nome, cpf, email e telefone e os atribui às propriedades correspondentes da classe.
    init {
        print("Usuario localizado \n")
    }

    fun cadastrarcliente() {
        println("Seja bem-vindo, $nome!")

        println("Digite o nome do cliente:")
        nome = readln()

        println("Digite o CPF do cliente:")
        cpf = readln()

        println("Digite o email do cliente:")
        email = readln()

        println("Digite o telefone do cliente:")
        telefone = readln()

        println("Digite a sua senha:")
        senha = readln()

    }


}