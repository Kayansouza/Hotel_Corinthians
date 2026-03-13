import model.Cliente
import security.service.Funcionario
import model.Quarto
import model.Reserva
import security.service.AuthService

// --- ESTADO GLOBAL ---
val nomeHotel = "Bando De Loucos"
var nomeUsuario: String = ""

val listaClientes = mutableListOf<Cliente>()
val listaQuartos = mutableListOf<Quarto>()
val listaReservas = mutableListOf<Reserva>()
val listaFuncionarios = mutableListOf<Funcionario>()

val quartos = Array(150) { "Livre" } // 150 quartos livres
val authService = AuthService()

fun main() {
    println("--- Bem-vindo ao Hotel $nomeHotel ---")
    fazerLoginInicial()
}

fun fazerLoginInicial() {

    print("Digite seu nome: ")
    nomeUsuario = readln()

    print("Digite a senha: ")
    val senha = readln()

    if (senha == "2678") {
        println("Bem-vindo ao Hotel $nomeHotel, $nomeUsuario. É um imenso prazer ter você por aqui!")
        inicio()
    } else {
        println("Senha incorreta! Por favor, tente novamente.")
        fazerLoginInicial()
    }
}

fun inicio() {

    var continuar = true

    while (continuar) {

        println("\nEscolha uma opção:")
        println("1 -> Cadastrar Cliente")
        println("2 -> Cadastrar Funcionário")
        println("3 -> Reserva de Quartos")
        println("4 -> Abastecimento de Automóveis")
        println("5 -> Sair do Hotel")

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> cadastrarCliente()
            2 -> cadastrarFuncionario()
            3 -> reservaDeQuartos()
            4 -> println("Funcionalidade de abastecimento em breve...")
            5 -> {
                println("Muito obrigado e até logo, $nomeUsuario.")
                continuar = false
            }
            else -> erro()
        }
    }
}

// ---------------------------
// CADASTRO DE CLIENTE
// ---------------------------

fun cadastrarCliente() {

    val novoCliente = coletarDadosDoUsuario()

    listaClientes.add(novoCliente)

    println("\n✅ Cliente ${novoCliente.nome} cadastrado com sucesso!")
}

// ---------------------------
// CADASTRO DE FUNCIONÁRIO
// ---------------------------

fun cadastrarFuncionario() {

    println("\n--- [Cadastrar Funcionário] ---")

    print("Nome: ")
    val nome = readln()

    print("CPF: ")
    val cpf = readln()

    print("Cargo: ")
    val cargo = readln()

    print("Senha: ")
    val senha = readln()

    val novoFuncionario = Funcionario(
        nome = nome,
        cpf = cpf,
        cargo = cargo,
        senha = senha,
        idade = TODO(),
    )

    listaFuncionarios.add(novoFuncionario)

    println("✅ Funcionário $nome cadastrado com sucesso!")
}

// ---------------------------
// COLETA DE DADOS CLIENTE
// ---------------------------

fun coletarDadosDoUsuario(): Cliente {

    println("\n--- [Coleta de Dados Cliente] ---")

    print("Nome: ")
    val nome = readln()

    print("Idade: ")
    val idade = readln().toIntOrNull() ?: 0

    print("CPF: ")
    val cpf = readln()

    print("Email: ")
    val email = readln()

    print("Telefone: ")
    val telefone = readln()

    print("Senha: ")
    val senha = readln()

    return Cliente(
        nome = nome,
        idade = idade,
        cpf = cpf,
        email = email,
        telefone = telefone,
        senha = senha
    )
}

// ---------------------------
// RESERVA DE QUARTOS
// ---------------------------

fun reservaDeQuartos() {

    println("\n--- Status dos Quartos ---")

    for (i in quartos.indices) {
        println("Quarto ${i + 1}: ${quartos[i]}")
    }

    println("\n---- Reservas VIPs Para Clientes Especiais! ----")

    println("Selecione o Evento:")
    println("1 - Aniversário do Corinthians (Dia 01)")
    println("2 - Grande Evento (Dias 06, 07 e 08)")

    val eventoEscolha = readln()

    val nomeEvento = when (eventoEscolha) {
        "1" -> "Aniversário do Corinthians"
        "2" -> "Grande Evento"
        else -> "Evento Padrão"
    }

    print("\nQual quarto você deseja reservar (1 a ${quartos.size})? ")

    val numQuarto = readln().toIntOrNull()

    if (numQuarto != null && numQuarto in 1..quartos.size) {

        val index = numQuarto - 1

        if (quartos[index] == "Livre") {

            quartos[index] = "Ocupado - $nomeEvento"

            println("✅ Sucesso! Quarto $numQuarto reservado para: $nomeEvento!")

        } else {

            println("❌ Quarto $numQuarto já está ocupado por: ${quartos[index]}")

        }

    } else {

        println("❌ Número de quarto inválido! Escolha entre 1 e ${quartos.size}.")

    }
}

// ---------------------------
// ERRO
// ---------------------------

fun erro() {
    println("Opção inválida, $nomeUsuario! Tente novamente.")
}