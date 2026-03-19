import controller.FuncionarioController
import model.Cliente
import controller.UsuarioController
import security.service.Funcionario
import security.service.PasswordService
import security.service.EmailService
import security.service.ReservaService
import model.Abastecimento

// --- ESTADO GLOBAL ---
val nomeHotel = "Bando De Loucos"
var nomeUsuario: String = ""
val listaClientes = mutableListOf<Cliente>()
val listaFuncionarios = mutableListOf<Funcionario>()
val quartos = Array(20) { "Livre" }
val funcionarioController = FuncionarioController()

val emailService = EmailService()
val usuarioController = UsuarioController()
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
            5 -> abastecimento()
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

    val (nomeEvento, valorDiaria) = when (eventoEscolha) {
        "1" -> "Aniversário do Corinthians" to 500.0
        "2" -> "Grande Evento" to 450.0
        else -> "Evento Padrão" to 300.0
    }

    print("Nome do Hóspede: ")
    val nomeHospede = readln()

    print("Número do Quarto (1-20): ")
    val n = readln().toIntOrNull() ?: 0

    if (n in 1..20 && quartos[n - 1] == "Livre") {

        print("Quantos dias de evento? ")
        val dias = readln().toIntOrNull() ?: 1
        val total = reservaService.calcularValorTotal(valorDiaria, dias)

        quartos[n - 1] = "Ocupado ($nomeHospede)"
        emailService.enviarConfirmaçao("cliente@email.com")
        println("✅ Check-in no quarto $n realizado para $nomeHospede ($nomeEvento)!")
        println("💰 Valor total para $dias dias: R$ ${"%.2f".format(total)}")
    } else {
        println("❌ Quarto indisponível ou inexistente.")
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
    println("\n--- CADASTRO DE CLIENTE ---")
    print("Nome: "); val nome = readln().trim()
    print("Ano de Nascimento: "); val inputAno = readln()
    print("Email: "); val email = readln().trim()
    print("Telefone: "); val tel = readln().trim()

    val anoValidado = usuarioController.validarAno(inputAno)

    // Usando .let para garantir segurança: só entra aqui se o ano for válido
    if (usuarioController.processarCadastro(email, anoValidado, listaClientes)) {
        anoValidado?.let { ano ->
            val novoCliente = Cliente(
                nome = nome,
                anoDeNascimento = anoValidado!!,
                cpf = "000",
                email = email,
                telefone = tel,
                senhaHash = "HASH_SENHA"
            )
            listaClientes.add(novoCliente)
            println("✅ Cadastro realizado com sucesso!")
        }
    } else {
        println("❌ Dados inválidos. Verifique se o e-mail é válido e o ano está entre 1900-2026.")
    }
}
fun cadastrarFuncionario() {
    println("\n--- CADASTRO DE FUNCIONÁRIO ---")

    // 1. Coleta os dados do funcionário
    print("Nome: "); val nome = readln().trim()
    print("Ano de Nascimento: "); val inputAno = readln()
    print("Email: "); val email = readln().trim()
    print("Código Corporativo: "); val codigo = readln().trim()

    // 2. Valida o ano usando a lógica base (do UsuarioController)
    val anoValidado = usuarioController.validarAno(inputAno)

    // 3. Processa tudo no FuncionarioController
    if (funcionarioController.processarCadastroFuncionario(email, anoValidado, codigo, listaFuncionarios)) {

        // 4. Se passou em tudo, registra!
        val novoFuncionario = Funcionario(
            nome = nome,
            anoDeNascimento = anoValidado!!,
            email = email,
            cpf = "000",
            cargo = "Staff",
            senhaHash = "HASH_SENHA"
        )
        listaFuncionarios.add(novoFuncionario)
        println("✅ Funcionário $nome registrado com sucesso!")

    } else {
        println("❌ Falha: Código inválido, e-mail mal formatado ou ano fora do intervalo.")
    }
}

fun abastecimento() {
    println("\n--- Posto Bando de Loucos ---") // Mantendo o padrão do seu projeto!
    println("1 - Gasolina (R$ 6.00) | 2 - Etanol (R$ 5.00) | 3 - Álcool (R$ 4.00)")
    print("Selecione o combustível: ")

    val escolha = readln()

    // Criando o objeto com base na escolha
    val abastecimento = when (escolha) {
        "1" -> Abastecimento("Gasolina", 6.0)
        "2" -> Abastecimento("Etanol", 5.0)
        "3" -> Abastecimento("Álcool", 4.0)
        else -> null
    }

    if (abastecimento != null) {
        print("Digite a quantidade de litros (ex: 10.5): ")

        // Tratando a entrada para aceitar vírgula ou ponto
        val litros = readln().replace(",", ".").toDoubleOrNull() ?: 0.0

        if (litros > 0) {
            exibirRecibo(abastecimento, litros)
        } else {
            println("Erro: Quantidade inválida.")
        }
    } else {
        println("Erro: Opção de menu inexistente.")
    }
}

// Função separada apenas para o Recibo (Single Responsibility Principle)
fun exibirRecibo(info: Abastecimento, qtd: Double) {
    val total = qtd * info.valorLitro
    println("\n========================")
    println("        RECIBO")
    println("========================")
    println("Produto:    ${info.tipo}")
    println("Preço Unit: R$ ${info.valorLitro}")
    println("Qtd:        $qtd L")
    println("TOTAL:      R$ ${"%.2f".format(total)}")
    println("========================\n")
}
fun erro() = println("❌ Opção inválida!")
