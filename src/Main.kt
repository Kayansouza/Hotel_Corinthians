import controller.ArCondicionadoController
import controller.EventoController
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
val reservasalao = mutableListOf<String>()
val eventoController = EventoController()
val arController = ArCondicionadoController()

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
        println("\n--- 🏨 HOTEL BANDO DE LOUCOS ---")
        // Aqui eu organizei o menu para chamar TODAS as suas funções
        println("1. Clientes | 2. Funcionários | 3. Reservas VIP (Quartos) | 4. Eventos (Auditórios)")
        println("5. Salão de Festas | 6. Check-out | 7. Abastecimento | 8. Ar-Condicionado | 9. Pesquisar Hóspede | 10. Sair")
        print("Opção: ")

        when (readln().toIntOrNull()) {
            1 -> cadastrarCliente()
            2 -> cadastrarFuncionario()
            3 -> reservaDeQuartos()    // CHAMA SUA LÓGICA VIP
            4 -> menuReservas()        // CHAMA SUA LÓGICA DE AUDITÓRIOS
            5 -> reservaSalaoFestas()  // CHAMA SUA LÓGICA DO SALÃO
            6 -> realizarCheckOut()
            7 -> abastecimento()       // CHAMA SUA LÓGICA DOS POSTOS
            8 -> arController.comparar(nomeUsuario)
            9 -> pesquisarHospede()    // CHAMA SUA LÓGICA DE BUSCA
            10 -> {
                println("Muito obrigado e até logo, $nomeUsuario.")
                continuar = false
            }
            else -> erro()
        }
    }
}
fun menuReservas() {
    println("\n--- [Eventos] $nomeHotel ---")

    // 1. Auditório
    print("Quantidade de convidados: ")
    val convidados = readln().toIntOrNull() ?: -1

    if (convidados <= 0 || convidados > 350) {
        println("❌ Número de convidados inválido.")
        return // Volta ao menu principal
    }

    val (auditorio, extras) = if (convidados <= 220) {
        val cadeirasExtras = if (convidados > 150) convidados - 150 else 0
        "Laranja" to cadeirasExtras
    } else {
        "Colorado" to 0
    }
    println("Auditório selecionado: $auditorio" + if (extras > 0) " ($extras cadeiras adicionais)" else "")

    // 2. Agenda (Dia e Hora)
    print("Dia da semana: ")
    val dia = readln().lowercase().trim() // Ex: segunda
    print("Hora inicial (0-23): ")
    val horaIni = readln().toIntOrNull() ?: -1
    print("Duração do evento (horas): ")
    val duracao = readln().toIntOrNull() ?: 10

    // Validação de horário
    val limiteHora = if (dia == "sabado" || dia == "domingo") 15 else 23
    if (horaIni < 7 || horaIni > limiteHora || (horaIni + duracao) > limiteHora) {
        println("❌ Erro: O hotel só aceita eventos iniciando entre 07h e $limiteHora h.")
        return
    } else if ((horaIni + duracao) > limiteHora) {
        println("❌ Erro: Com essa duração, o evento terminaria às ${horaIni + duracao}h," +
                " ultrapassando o limite de $limiteHora h.")
        return
    }

    print("Nome da empresa: ")
    val empresa = readln()

    // 3. Garçons e Buffet
    // Regra: 1 garçom a cada 12 pessoas + 1 a cada 2 horas de evento
    val garconsBase = Math.ceil(convidados / 12.0).toInt()
    val garconsReforco = Math.floor(duracao / 2.0).toInt()
    val totalGarcons = garconsBase + garconsReforco
    val custoGarcons = totalGarcons * duracao * 10.50

    // Buffet
    val cafeL = convidados * 0.2
    val aguaL = convidados * 0.5
    val salgados = convidados * 7
    val custoBuffet = (cafeL * 0.80) + (aguaL * 0.40) + (Math.ceil(salgados / 100.0) * 34.00)

    val totalGeral = custoGarcons + custoBuffet

    // 4. Relatório Final
    println("\n--- RESUMO DO EVENTO ---")
    println("Empresa: $empresa | Auditório: $auditorio")
    println("Data: $dia às ${horaIni}h (Duração: ${duracao}h)")
    println("Garçons: $totalGarcons | Custo: R$ ${"%.2f".format(custoGarcons)}")
    println("Buffet: R$ ${"%.2f".format(custoBuffet)}")
    println("TOTAL DO EVENTO: R$ ${"%.2f".format(totalGeral)}")

    print("\n$nomeUsuario, confirma a reserva? (S/N): ")
    if (readln().uppercase() == "S") {
        println("✅ Reserva efetuada com sucesso.")
        // Aqui você pode somar o totalGeral em uma variável global de receita para o relatório final
    } else {
        println("Reserva não efetuada.")
    }
}
val datasOcupadasSalao = mutableListOf<String>()

fun reservaSalaoFestas() {
    println("\n---- 🎊 Reserva do Salão de Festas ----")

    print("Digite o e-mail do cliente: ")
    val email = readln().trim()
    val cliente = listaClientes.find { it.email.equals(email, ignoreCase = true) }

    if (cliente == null) {
        println("❌ Erro: Cliente não encontrado. Cadastre-o primeiro.")
        return
    }

    print("Para qual data deseja reservar o salão? (ex: 25/12): ")
    val dataDesejada = readln().trim()

    // CORREÇÃO 1: tinha um readln() solto aqui sem print() nenhum antes,
    // o usuário ficava travado sem saber o que digitar. Removido!

    if (datasOcupadasSalao.contains(dataDesejada)) {
        println("⚠️ Sinto muito, o salão já está ocupado no dia $dataDesejada!")

        // CORREÇÃO 2: antes chamava reservaSalaoFestas() direto sem perguntar nada,
        // agora pergunta primeiro se quer outra data
        print("Deseja escolher outra data? (s/n): ")
        val resposta = readln().trim().lowercase()
        if (resposta == "s") {
            reservaSalaoFestas()
        } else {
            println("Voltando ao menu...")
        }
    } else {
        datasOcupadasSalao.add(dataDesejada)
        cliente.historico.add("Reserva Salão: Data $dataDesejada")
        println("✅ Sucesso! Salão reservado para ${cliente.nome} no dia $dataDesejada.")
    }
}

fun reservaDeQuartos() {
    println("\n---- [Reservas] $nomeHotel ----")

    // Suas validações originais de diária
    print("Informe o valor da diária: ")
    val valorDiaria = readln().toDoubleOrNull() ?: 0.0
    print("Informe a quantidade de diárias (1-30): ")
    val dias = readln().toIntOrNull() ?: 0

    if (valorDiaria <= 0 || dias !in 1..30) {
        println("❌ Valor Inválido, $nomeUsuario")
        return // Volta pro menu como você queria
    }

    print("Informe o nome do hóspede: ")
    val nomeHospede = readln()

    // O NOVO: Tipo de quarto (Fatores do Terabithia)
    print("Tipo de quarto (S-Standard | E-Executivo | L-Luxo): ")
    val tipo = readln().uppercase()
    val fator = when(tipo) {
        "E" -> 1.35
        "L" -> 1.65
        else -> 1.0
    }

    // Sua lógica de escolher quarto (1-20)
    print("Escolha um quarto (1-20): ")
    val n = readln().toIntOrNull() ?: 0

    if (n !in 1..20 || quartos[n-1] != "Livre") {
        println("⚠️ Quarto ocupado ou inválido!")
        // Aqui você pode listar os livres usando quartos.forEachIndexed
        return
    }

    // CÁLCULOS (O que mudou)
    val subtotal = valorDiaria * dias * fator
    val taxa = subtotal * 0.10
    val totalFinal = subtotal + taxa

    println("\n--- RESUMO ---")
    println("Hóspede: $nomeHospede | Quarto: $n")
    println("Total: R$ ${"%.2f".format(totalFinal)}")

    print("$nomeUsuario, confirma a reserva? (S/N): ")
    if (readln().uppercase() == "S") {
        quartos[n-1] = "Ocupado ($nomeHospede)"
        println("✅ Reserva efetuada!")
    }
}

fun realizarCheckOut() {
    println("-------- Sistema de Check-out --------")
    print("Número do Quarto para Check-out: ")

    val n = readln().toIntOrNull() ?: 0

    if (n in 1..20) {
        val statusAtual = quartos[n - 1]

        if (statusAtual != "Livre") {
            println("Quarto $n encontrado: $statusAtual")
            print("Confirma saída e limpeza do quarto? (S/N): ")
            val confirma = readln().uppercase()

            if (confirma == "S") {
                quartos[n - 1] = "Livre"
                println("✅ Check-out realizado! O quarto $n agora está disponível e limpo.")
            } else {
                println("Ação cancelada. O quarto continua reservado.")
            }
        } else {
            println("❌ O quarto $n já está livre.")
        }
    } else {
        println("❌ Quarto inexistente.")
    }
}

fun pesquisarHospede() {
    println("\n--- Pesquisar Hóspede ---")
    print("Digite o início do nome (Prefixo): ")
    val busca = readln().lowercase()

    // Filtra a sua lista original
    val encontrados = listaClientes.filter { it.nome.lowercase().startsWith(busca) }

    if (encontrados.isEmpty()) {
        println("❌ Hóspede não encontrado.")
    } else {
        println("Resultados encontrados:")
        encontrados.forEach { cliente ->
            println("- ${cliente.nome} (E-mail: ${cliente.email})")
        }
    }
}

fun cadastrarCliente() {
    println("\n--- CADASTRO DE CLIENTE ---")
    print("Nome: "); val nome = readln().trim()
    print("Ano de Nascimento: "); val inputAno = readln()
    print("Email: "); val email = readln().trim()
    print("Telefone: "); val tel = readln().trim()

    val emailDuplicado = listaClientes.any { it.email.equals(email, ignoreCase = true) }
    if (emailDuplicado) {
        println("❌ Erro: Este e-mail já está cadastrado para outro cliente.")
        return
    }

    val anoValidado = usuarioController.validarAno(inputAno)

    if (usuarioController.processarCadastro(email, anoValidado, listaClientes)) {
        anoValidado?.let { ano ->
            val novoCliente = Cliente(
                nome = nome,
                anoDeNascimento = ano,
                cpf = "000",
                email = email,
                telefone = tel,
                senhaHash = "HASH_SENHA"
            )
            listaClientes.add(novoCliente)
            println("✅ Cadastro de $nome realizado com sucesso!")
        }
    } else {
        println("❌ Dados inválidos. Verifique o e-mail e o ano (1900-2026).")
    }
}

fun cadastrarFuncionario() {
    println("\n--- CADASTRO DE FUNCIONÁRIO ---")
    print("Nome: "); val nome = readln().trim()
    print("Ano de Nascimento: "); val inputAno = readln()
    print("Email: "); val email = readln().trim()
    print("Cargo: "); val cargo = readln().trim()
    print("Código Corporativo: "); val codigo = readln().trim()

    val anoValidado = usuarioController.validarAno(inputAno)

    if (funcionarioController.processarCadastroFuncionario(email, anoValidado, codigo, listaFuncionarios)) {
        val novoFuncionario = Funcionario(
            nome = nome,
            anoDeNascimento = anoValidado!!,
            email = email,
            cpf = "000",
            cargo = cargo,
            senhaHash = "HASH_SENHA"
        )
        listaFuncionarios.add(novoFuncionario)
        println("✅ Funcionário $nome registrado com sucesso!")
    } else {
        println("❌ Falha: Código inválido, e-mail mal formatado ou ano fora do intervalo.")
    }
}

fun abastecimento() {
    println("\n--- [Abastecimento] $nomeHotel ---")

    // Entrada de dados: Wayne Oil
    println("Posto Wayne Oil:")
    print("Preço do Álcool: ")
    val alcoolWayne = readln().replace(",", ".").toDoubleOrNull() ?: 0.0
    print("Preço da Gasolina: ")
    val gasolinaWayne = readln().replace(",", ".").toDoubleOrNull() ?: 0.0

    // Entrada de dados: Stark Petrol
    println("\nPosto Stark Petrol:")
    print("Preço do Álcool: ")
    val alcoolStark = readln().replace(",", ".").toDoubleOrNull() ?: 0.0
    print("Preço da Gasolina: ")
    val gasolinaStark = readln().replace(",", ".").toDoubleOrNull() ?: 0.0

    val tanque = 42.0

    // Lógica para o Wayne Oil
    val (combustivelWayne, precoWayne) = if (alcoolWayne <= gasolinaWayne * 0.7) {
        "Álcool" to alcoolWayne
    } else {
        "Gasolina" to gasolinaWayne
    }
    val totalWayne = precoWayne * tanque

    // Lógica para o Stark Petrol
    val (combustivelStark, precoStark) = if (alcoolStark <= gasolinaStark * 0.7) {
        "Álcool" to alcoolStark
    } else {
        "Gasolina" to gasolinaStark
    }
    val totalStark = precoStark * tanque

    // Exibição dos resultados
    println("\n${"%.2f".format(totalWayne)} - Wayne Oil: melhor opção = $combustivelWayne")
    println("${"%.2f".format(totalStark)} - Stark Petrol: melhor opção = $combustivelStark")

    // Veredito Final
    if (totalWayne < totalStark) {
        println("\n$nomeUsuario, é mais barato abastecer com $combustivelWayne no posto Wayne Oil.")
    } else {
        println("\n$nomeUsuario, é mais barato abastecer com $combustivelStark no posto Stark Petrol.")
    }
}
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