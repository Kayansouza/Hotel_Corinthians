import model.Cliente
import security.service.Funcionario
import security.service.PasswordService
import security.service.EmailService
import controller.UsuarioController
import security.service.ReservaService

// ---------------------------
// ESTADO GLOBAL
// ---------------------------
val nomeHotel = "Bando De Loucos"
var nomeUsuario: String = ""
val listaClientes = mutableListOf<Cliente>()
val listaFuncionarios = mutableListOf<Funcionario>()
val quartos = Array(20) { "Livre" }

val emailService = EmailService()
val usuarioController = UsuarioController(emailService)
val passwordService = PasswordService()
val reservaService = ReservaService()

fun main() {
    println("--- Bem-vindo ao Hotel $nomeHotel ---")
    fazerLoginInicial()
}

fun fazerLoginInicial() {
    var loginSucesso = false
    while (!loginSucesso) {
        print("\nUsuário: ")
        nomeUsuario = readln()
        print("Senha: ")
        if (readln() == "2678") {
            loginSucesso = true
            inicio()
        } else {
            println("❌ Senha incorreta.")
        }
    }
}

fun inicio() {
    var continuar = true
    while (continuar) {
        println("\n--- MENU PRINCIPAL ---")
        println("1. Cadastrar Cliente | 2. Cadastrar Funcionário | 3. Menu Reservas | 4. Check-out | 5. Abastecimento | 6. Sair")
        print("Opção: ")

        when (readln().toIntOrNull()) {
            1 -> cadastrarCliente()
            2 -> cadastrarFuncionario()
            3 -> menuReservas()
            4 -> realizarCheckOut()
            5 -> println("\n⛽ Funcionalidade de abastecimento ativa.")
            6 -> continuar = false
            else -> erro()
        }
    }
}

fun menuReservas() {
    var subMenuAtivo = true
    while (subMenuAtivo) {
        println("\n--- SUB-MENU RESERVAS ---")
        println("1. Check-in (Com Evento) | 2. Voltar")

        when (readln().toIntOrNull()) {
            1 -> reservaDeQuartos()
            2 -> subMenuAtivo = false
            else -> println("❌ Opção Inválida")
        }
    }
}

fun reservaDeQuartos() {
    println("\n---- Reservas VIPs ----")
    println("1 - Aniversário do Corinthians | 2 - Grande Evento | 3 - Evento Padrão")
    print("Escolha o tipo de evento: ")
    val eventoEscolha = readln()
    val nomeEvento = when (eventoEscolha) {
        "1" -> "Aniversário do Corinthians"
        "2" -> "Grande Evento"
        else -> "Evento Padrão"
    }

    print("Número do Quarto (1-20): ")
    val n = readln().toIntOrNull() ?: 0
    if (n in 1..20 && quartos[n - 1] == "Livre") {
        quartos[n - 1] = "Ocupado - $nomeEvento"
        println("✅ Check-in no quarto $n realizado para: $nomeEvento!")
    } else {
        println("❌ Quarto indisponível.")
    }
}

fun realizarCheckOut() {
    print("Número do Quarto para Check-out: ")
    val n = readln().toIntOrNull() ?: 0
    if (n in 1..20 && quartos[n - 1].contains("Ocupado")) {
        quartos[n - 1] = "Livre"
        emailService.enviarConfirmaçao("cliente@email.com")
        println("✅ Check-out concluído!")
    } else {
        println("❌ Este quarto não possui reserva ativa.")
    }
}

fun cadastrarCliente() {
    print("Nome: "); val nome = readln()
    print("Email: "); val email = readln()
    if (usuarioController.processarCadastro(email)) {
        listaClientes.add(Cliente(nome, 0, "000", email, "00", "HASH_SENHA"))
        println("✅ Cliente registrado.")
    } else {
        println("❌ Email inválido.")
    }
}

fun cadastrarFuncionario() {
    print("Nome: "); val nome = readln()
    listaFuncionarios.add(Funcionario(nome, 0, "000", "Staff", "1234"))
    println("✅ Funcionário registrado.")
}

fun erro() = println("❌ Opção inválida!")