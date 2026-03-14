import model.Cliente
import security.service.Funcionario
import security.service.AuthService
import security.service.PasswordService
import security.service.EmailService
import controller.UsuarioController



// ---------------------------
// ESTADO GLOBAL
// ---------------------------

val nomeHotel = "Bando De Loucos"
var nomeUsuario: String = ""
val listaClientes = mutableListOf<Cliente>()
val listaFuncionarios = mutableListOf<Funcionario>()
val quartos = Array(20) { "Livre" }
val authService = AuthService()


// ---------------------------
// MAIN
// ---------------------------

fun main() {

    println("--- Bem-vindo ao Hotel $nomeHotel ---")

    fazerLoginInicial()


}

// ---------------------------
// LOGIN
// ---------------------------

fun fazerLoginInicial() {

    print("Digite seu nome: ")
    nomeUsuario = readln()

    print("Digite a senha: ")
    val senha = readln()

    if (senha == "2678") {

        println("Bem-vindo ao Hotel $nomeHotel, $nomeUsuario.")
        inicio()

    } else {

        println("Senha incorreta! Tente novamente.")
        fazerLoginInicial()

    }
}

// ---------------------------
// MENU PRINCIPAL
// ---------------------------

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
// CADASTRAR CLIENTE
// ---------------------------

fun cadastrarCliente() {
    println("\n--- [Coleta de Dados Cliente] ---")

    print("Nome: ")
    val nome = readln()

    print("Idade: ")
    val idade = readln().toIntOrNull() ?: 0

    print("CPF: ")
    val cpf = readln()

    // Adicionando o Service e o Controller para validar o e-mail

    val emailService = EmailService()
    val usuarioController = UsuarioController(emailService)

    var email = ""
    var emailValido = false

    // Loop para FORÇAR o e-mail correto antes de seguir
    while (!emailValido) {
        print("Email: ")
        email = readln()

        // O Controller pede pro Service validar e disparar o e-mail de confirmação
        emailValido = usuarioController.processarCadastro(email)

        if (!emailValido) {
            println("❌ E-mail inválido! O formato correto é nome@dominio.com. Tente novamente.")
        }
    }
    // --- FIM DA MUDANÇA ---

    print("Telefone: ")
    val telefone = readln()

    val passwordService = PasswordService()
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
    println("\n✅ Cliente ${novoCliente.nome} cadastrado e e-mail de confirmação enviado!")
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
// COLETA DE DADOS DO CLIENTE
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

    println("1 - Aniversário do Corinthians")
    println("2 - Grande Evento")
    println("3 - Evento Padrão")

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

            println("✅ Sucesso! $nomeUsuario Quarto $numQuarto reservado para: $nomeEvento!")

        } else {

            println("❌ Quarto $numQuarto já está ocupado por: ${quartos[index]}")

        }

    } else {

        println("❌ Número de quarto inválido!")

    }
}

// ---------------------------
// ERRO
// ---------------------------

fun erro() {

    println("Opção inválida, $nomeUsuario! Tente novamente.")

}
