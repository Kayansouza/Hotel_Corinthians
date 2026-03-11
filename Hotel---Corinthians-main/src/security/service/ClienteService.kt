package security.service

import model.Cliente

class ClienteService {

    fun cadastrarClienteViaConsole(): Cliente {
        println("=== Cadastro de Cliente ===")

        print("Nome: ")
        val nome = readln()

        print("CPF: ")
        val cpf = readln()

        print("Email: ")
        val email = readln()

        print("Telefone: ")
        val telefone = readln()

        print("Senha: ")
        val senha = readln()

        val cliente = Cliente(
            nome = nome,
            cpf = cpf,
            email = email,
            telefone = telefone,
            senha = senha
        )

        println("\nCliente cadastrado com sucesso!")
        println("Bem-vindo, ${cliente.nome}!\n")

        return cliente
    }
}