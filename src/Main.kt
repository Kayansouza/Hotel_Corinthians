import controller.*
import model.Cliente
import security.service.Funcionario
import security.service.EmailService
import security.service.ReservaService
import model.Abastecimento
import kotlin.math.ceil
import kotlin.math.floor
import model.teste

// --- 🌍 ESTADO GLOBAL (ÚNICO E CORRIGIDO) ---
val nomeHotel = "Bando De Loucos"
var nomeUsuario: String = ""
val listaClientes = mutableListOf<Cliente>()
val listaFuncionarios = mutableListOf<Funcionario>()
val quartos = Array(20) { "Livre" }
val datasOcupadasSalao = mutableListOf<String>()

// Acumuladores para o Relatório Operacional
var receitaHospedagem: Double = 0.0
var receitaEventos: Double = 0.0
var totalEventosConfirmados: Int = 0

// Instâncias dos Controllers
val eventoController = EventoController()
val arController = ArCondicionadoController()
val funcionarioController = FuncionarioController()
val usuarioController = UsuarioController()

fun main() {
    println("--- 🏨 Bem-vindo ao Hotel $nomeHotel ---")
    fazerLoginInicial()
}

// --- 🔐 LOGIN (Requisito 3.1: 3 tentativas) ---
fun fazerLoginInicial() {
    var tentativas = 0
    val senhaCorreta = "2678"

    while (tentativas < 3) {
        print("\nUsuário: ")
        nomeUsuario = readln()
        print("Senha: ")
        if (readln() == senhaCorreta) {
            println("✅ Bem-vindo ao Hotel $nomeHotel, $nomeUsuario. É um imenso prazer!")
            inicio()
            return
        } else {
            tentativas++
            println("❌ Senha incorreta. Tentativa $tentativas de 3.")
        }
    }
    println("🚫 Acesso bloqueado. O programa será encerrado.")
}

// --- 📑 MENU PRINCIPAL ---
fun inicio() {
    var continuar = true
    while (continuar) {
        println("\n--- 🏁 MENU OPERACIONAL - $nomeHotel ---")
        println("1. Clientes | 2. Funcionários | 3. Reservas VIP (Quartos) | 4. Eventos (Auditórios)")
        println("5. Salão de Festas | 6. Check-out | 7. Abastecimento | 8. Ar-Condicionado")
        println("9. Pesquisar Hóspede | 10. Relatório Geral | 11. Sair")
        print("Opção: ")

        when (readln().toIntOrNull()) {
            1 -> cadastrarCliente()
            2 -> cadastrarFuncionario()
            3 -> reservaDeQuartos()
            4 -> menuReservas()
            5 -> reservaSalaoFestas()
            6 -> realizarCheckOut()
            7 -> abastecimento()
            8 -> arController.comparar(nomeUsuario)
            9 -> pesquisarHospede()
            10 -> exibirReceita()
            11 -> {
                println("Muito obrigado e até logo, $nomeUsuario. Vai Corinthians!")
                continuar = false
            }
            else -> println("❌ Opção inválida!")
        }
    }
}

// --- 🛌 RESERVA DE QUARTOS (Subprograma 1) ---
fun reservaDeQuartos() {
    println("\n---- [Reservas] $nomeHotel ----")
    print("Informe o valor da diária: ")
    val valorDiaria = readln().toDoubleOrNull() ?: 0.0
    print("Informe a quantidade de diárias (1-30): ")
    val dias = readln().toIntOrNull() ?: 0

    if (valorDiaria <= 0 || dias !in 1..30) {
        println("❌ Valor Inválido, $nomeUsuario")
        return
    }

    print("Informe o nome do hóspede: ")
    val nomeH = readln()
    print("Tipo de quarto (S-Standard | E-Executivo | L-Luxo): ")
    val fator = when(readln().uppercase()) {
        "E" -> 1.35
        "L" -> 1.65
        else -> 1.0
    }

    println("\nMapa de Quartos:")
    for (i in 0 until 20) {
        val status = if (quartos[i] == "Livre") "L" else "O"
        print("[$status] ")
        if ((i + 1) % 5 == 0) println()
    }

    print("Escolha um quarto (1-20): ")
    val n = readln().toIntOrNull() ?: 0

    if (n !in 1..20 || quartos[n-1] != "Livre") {
        println("⚠️ Quarto ocupado ou inválido!")
        return
    }

    val totalFinal = (valorDiaria * dias * fator) * 1.10
    println("Total: R$ ${"%.2f".format(totalFinal)}")
    print("Confirma reserva? (S/N): ")
    if (readln().uppercase() == "S") {
        quartos[n-1] = "Ocupado ($nomeH)"
        receitaHospedagem += totalFinal // Soma no Relatório
        println("✅ Reserva efetuada!")
    }
}

// --- 🎭 EVENTOS (Subprograma 3) ---
fun menuReservas() {
    println("\n--- [Eventos] $nomeHotel ---")
    print("Quantidade de convidados: ")
    val convidados = readln().toIntOrNull() ?: -1

    if (convidados <= 0 || convidados > 350) {
        println("❌ Número de convidados inválido.")
        return
    }

    val (auditorio, extras) = if (convidados <= 220) {
        val cadeirasExtras = if (convidados > 150) convidados - 150 else 0
        "Laranja" to cadeirasExtras
    } else "Colorado" to 0

    print("Dia: "); val dia = readln().lowercase().trim()
    print("Hora inicial (7-23): "); val hora = readln().toIntOrNull() ?: 0
    print("Duração: "); val duracao = readln().toIntOrNull() ?: 1

    val limite = if (dia == "sabado" || dia == "domingo") 15 else 23
    if (hora < 7 || (hora + duracao) > limite) {
        println("❌ Auditório indisponível neste horário.")
        return
    }

    val totalGarcons = ceil(convidados / 12.0).toInt() + floor(duracao / 2.0).toInt()
    val custoGarcons = totalGarcons * duracao * 10.50
    val custoBuffet = (convidados * 0.2 * 0.8) + (convidados * 0.5 * 0.4) + (ceil((convidados * 7) / 100.0) * 34.0)
    val totalGeral = custoGarcons + custoBuffet

    println("TOTAL DO EVENTO: R$ ${"%.2f".format(totalGeral)}")
    print("Confirma? (S/N): ")
    if (readln().uppercase() == "S") {
        receitaEventos += totalGeral // Soma no Relatório
        totalEventosConfirmados++
        println("✅ Evento Reservado!")
    }
}

// --- 🎊 SALÃO DE FESTAS ---
fun reservaSalaoFestas() {
    println("\n---- 🎊 Reserva do Salão ----")
    print("E-mail do cliente: ")
    val email = readln().trim()
    val cliente = listaClientes.find { it.email.equals(email, ignoreCase = true) }

    if (cliente == null) {
        println("❌ Cliente não encontrado.")
        return
    }

    print("Data (ex: 25/12): ")
    val data = readln().trim()

    if (datasOcupadasSalao.contains(data)) {
        println("⚠️ Salão ocupado em $data!")
    } else {
        datasOcupadasSalao.add(data)
        println("✅ Sucesso! Salão reservado para ${cliente.nome} em $data.")
    }
}

// --- 🧾 RELATÓRIO OPERACIONAL (Subprograma 6) ---
fun exibirReceita() {
    println("\n====================================================")
    println("      📊 RELATÓRIO OPERACIONAL - $nomeHotel")
    println("====================================================")
    val quartosOcupados = quartos.count { it != "Livre" }
    val taxaOcupacao = (quartosOcupados.toDouble() / 20.0) * 100

    println(" ITEM                         | VALOR             ")
    println("------------------------------|---------------------")
    println(" Reservas de Quartos          | $quartosOcupados")
    println(" Taxa de Ocupação             | ${"%.1f".format(taxaOcupacao)}%")
    println(" Hóspedes Cadastrados         | ${listaClientes.size}")
    println(" Eventos Realizados           | $totalEventosConfirmados")
    println("------------------------------|---------------------")
    println(" RECEITA HOSPEDAGENS          | R$ ${"%.2f".format(receitaHospedagem)}")
    println(" RECEITA EVENTOS             | R$ ${"%.2f".format(receitaEventos)}")
    println("------------------------------|---------------------")
    println(" TOTAL GERAL ACUMULADO        | R$ ${"%.2f".format(receitaHospedagem + receitaEventos)}")
    println("====================================================\n")
}

// --- AUXILIARES ---
fun realizarCheckOut() {
    print("Número do Quarto (1-20): ")
    val n = readln().toIntOrNull() ?: 0
    if (n in 1..20 && quartos[n-1] != "Livre") {
        quartos[n-1] = "Livre"
        println("✅ Check-out realizado!")
    } else println("❌ Quarto já está livre ou inválido.")
}

fun pesquisarHospede() {
    print("Início do nome: ")
    val busca = readln().lowercase()
    val encontrados = listaClientes.filter { it.nome.lowercase().startsWith(busca) }
    if (encontrados.isEmpty()) println("❌ Nenhum encontrado.")
    else encontrados.forEach { println("- ${it.nome} (${it.email})") }
}

fun cadastrarCliente() {
    print("Nome: "); val nome = readln().trim()
    print("Email: "); val email = readln().trim()
    if (listaClientes.any { it.email == email }) {
        println("❌ Email já existe.")
        return
    }
    listaClientes.add(Cliente(nome = nome, email = email))
    println("✅ $nome cadastrado!")
}

fun cadastrarFuncionario() = println("⚠️ Funcionalidade via FuncionarioController ativa.")

fun abastecimento() {
    println("\n--- [Abastecimento] ---")
    print("Wayne Álcool: "); val wa = readln().toDoubleOrNull() ?: 0.0
    print("Wayne Gasosa: "); val wg = readln().toDoubleOrNull() ?: 0.0
    print("Stark Álcool: "); val sa = readln().toDoubleOrNull() ?: 0.0
    print("Stark Gasosa: "); val sg = readln().toDoubleOrNull() ?: 0.0

    val precoW = if (wa <= wg * 0.7) wa else wg
    val precoS = if (sa <= sg * 0.7) sa else sg

    if (precoW < precoS) println("✅ Mais barato no Wayne Oil.")
    else println("✅ Mais barato no Stark Petrol.")
}