import model.Cliente
import security.service.Funcionario
import security.service.AuthService
import security.service.PasswordService
import security.service.EmailService
import controller.UsuarioController

// ---------------------------
// ESTADO GLOBAL (Instâncias Únicas)
// ---------------------------
val nomeHotel = "Bando De Loucos"
var nomeUsuario: String = ""

val listaClientes = mutableListOf<Cliente>()
val listaFuncionarios = mutableListOf<Funcionario>()
val quartos = Array(20) { "Livre" }

// Injeção de dependência para uso global
val emailService = EmailService()
val usuarioController = UsuarioController(emailService)
val passwordService = PasswordService()

// ---------------------------
// MAIN
// ---------------------------
fun main() {
    println("--- Bem-vindo ao Hotel $nomeHotel ---")
    fazerLoginInicial()
}

// ---------------------------
// LOGIN (Corrigido com Loop)
// ---------------------------
fun fazerLoginInicial() {
    var loginSucesso = false

    while (!loginSucesso) {
        print("\nDigite seu nome: ")
        nomeUsuario = readln()

        print("Digite a senha: ")
        val senha = readln()

        // Validação simples de senha
        if (senha == "2678") {
            println("\n✅ Bem-vindo ao Hotel $nomeHotel, $nomeUsuario.")
            loginSucesso = true
            inicio()
        } else {
            println("❌ Senha incorreta ou vazia! Tente novamente.")
        }
    }
}

// ---------------------------
// MENU PRINCIPAL
// ---------------------------
fun inicio() {
    var continuar = true

    while (continuar) {
        println("\n--- MENU PRINCIPAL ---")
        println("1 -> Cadastrar Cliente")
        println("2 -> Cadastrar Funcionário")
        println("3 -> Reserva de Quartos")
        println("4 -> Abastecimento de Automóveis")
        println("5 -> Sair do Hotel")
        print("Opção: ")

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> cadastrarCliente()
            2 -> cadastrarFuncionario()
            3 -> reservaDeQuartos()
            4 -> println("\n⛽ Funcionalidade de abastecimento em breve...")
            5 -> {
                println("\nMuito obrigado e até logo, $nomeUsuario. Vai Corinthians!")
                continuar = false
            }
            else -> erro()
        }
    }
}

// ---------------------------
// CADASTRAR CLIENTE (Com Validação de Email)
// ---------------------------
fun cadastrarCliente() {
    println("\n--- [Coleta de Dados Cliente] ---")

    print("Nome: ")
    val nome = readln()

    print("Idade: ")
    val idade = readln().toIntOrNull() ?: 0

    print("CPF: ")
    val cpf = readln()

    // Lógica para forçar e-mail válido
    var email = ""
    var emailValido = false

    while (!emailValido) {
        print("Email: ")
        email = readln()

        // O Controller valida via EmailService
        emailValido = usuarioController.processarCadastro(email)

        if (!emailValido) {
            println("❌ E-mail inválido! Use o formato: usuario@dominio.com")
        }
    }

    print("Telefone: ")
    val telefone = readln()

    // Uso do PasswordService para segurança
    println("Defina uma senha para o cliente:")
    val senhaDigitada = passwordService.lerSenha()
    val senhaHash = passwordService.criptografarSenha(senhaDigitada)

    val novoCliente = Cliente(
        nome = nome,
        idade = idade,
        cpf = cpf,
        email = email,
        telefone = telefone,
        senha = senhaHash
    )

    listaClientes.add(novoCliente)
    println("\n✅ Cliente ${novoCliente.nome} cadastrado com sucesso!")
}

// ---------------------------
// CADASTRAR FUNCIONÁRIO
// ---------------------------
fun cadastrarFuncionario() {
    println("\n--- [Cadastrar Funcionário] ---")

    print("Nome: ")
    val nome = readln()

    print("Idade: ")
    val idade = readln().toIntOrNull() ?: 0

    print("CPF: ")
    val cpf = readln()

    print("Cargo: ")
    val cargo = readln()

    print("Senha: ")
    val senha = readln()

    val novoFuncionario = Funcionario(
        nome = nome,
        idade = idade,
        cpf = cpf,
        cargo = cargo,
        senha = senha
    )

    listaFuncionarios.add(novoFuncionario)
    println("✅ Funcionário $nome cadastrado com sucesso!")
}

// ---------------------------
// RESERVA DE QUARTOS
// ---------------------------
fun reservaDeQuartos() {
    println("\n--- Status dos Quartos ---")
    for (i in quartos.indices) {
        println("Quarto ${i + 1}: ${quartos[i]}")
    }

    println("\n---- Reservas VIPs ----")
    println("1 - Aniversário do Corinthians")
    println("2 - Grande Evento")
    println("3 - Evento Padrão")
    print("Escolha o tipo de evento: ")

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
            println("✅ Sucesso! $nomeUsuario reservou o Quarto $numQuarto para: $nomeEvento!")
        } else {
            println("❌ Quarto $numQuarto já está ocupado por: ${quartos[index]}")
        }
    } else {
        println("❌ Número de quarto inválido!")
    }
}

fun erro() {
    println("❌ Opção inválida, $nomeUsuario! Tente novamente.")
}