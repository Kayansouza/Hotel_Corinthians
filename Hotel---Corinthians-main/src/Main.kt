import model.Cliente
import model.Usuario


import security.service.AuthService
val listaClientes = mutableListOf<Cliente>() // Lista global para armazenar os clientes cadastrados


fun main() {
    println("--- Bem-vindo ao Hotel Bando De Loucos ---")
    inicio()
}

fun inicio() {
    var continuar = true

    while (continuar) {
        println("\nEscolha uma opção:")
        println("1 -> Cadastrar Cliente")
        println("2 -> Entrar com Senha")
        println("3 -> Abastecimento de Automóveis")
        println("4 -> Sair do Hotel")

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> cadastrarCliente()
            2 -> entrarComSenha()
            3 -> println("Funcionalidade de abastecimento em breve...")
            4 -> {
                println("Obrigado por usar o nosso hotel! Saindo...")
                continuar = false
            }
            else -> println("Opção inválida! Tente novamente.")
        }
    }
}

 // A função cadastrarCliente é resposável por coletar as informações necessárias para o cadastro.

fun cadastrarCliente() {
    println("\n--- Cadastro de Cliente ---")

    print("Por favor, informe o seu nome: ")
    val nome = readln()

    print("Digite o CPF: ")
    val cpf = readln()

    print("Por favor, informe o email: ")
    val email = readln()

    print("Por favor, informe o telefone: ")
    val telefone = readln()

    print("Por favor, informe a senha: ")
    val senha = readln()

    // Instanciando os objetos
    val pessoa = Cliente(nome, cpf, email, telefone, senha)
    // val user = Usuario(nome, cpf, email, telefone, senha)

    println("\nCliente $nome cadastrado com sucesso!")
}


val authService = AuthService() // instanciando o serviço de autenticação para uso posterior

fun entrarComSenha() {

    println("\n--- [Login] ---")
    print("CPF: "); val cpf = readln()
    print("Senha: "); val senha = readln()

    // O main pergunta apenas pergunta: "Este serviço é valido?"
    val usuarioAutenticado = authService.validarLogin(cpf, senha, listaClientes)

    if (usuarioAutenticado != null) {
        // Acedemos apenas ao nome para ficar uma mensagem amigável
        println(" Login bem-sucedido! Bem-vindo(a), ${usuarioAutenticado.nome}")

        // Aqui poderias chamar a próxima função, ex: menuLogado()
    } else {
        // Mensagem de erro clara
        println(" Login falhou! CPF ou senha incorretos.")
    }
}

