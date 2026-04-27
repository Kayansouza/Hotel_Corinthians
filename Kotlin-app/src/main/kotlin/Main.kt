import state.HotelState
import controller.*
import kotlin.math.ceil
import kotlin.math.floor

var nomeUsuario: String = ""

val eventoController = EventoController()
val arController = ArCondicionadoController()
val funcionarioController = FuncionarioController()
val usuarioController = UsuarioController()

fun main() {
    println("--- 🏨 Bem-vindo ao Hotel ${HotelState.nomeHotel} ---")
    fazerLoginInicial()
}

// --- 🔐 LOGIN ---
fun fazerLoginInicial() {
    var tentativas = 0
    val senhaCorreta = "2678"

    while (tentativas < 3) {
        print("\nUsuário: ")
        nomeUsuario = readln()

        print("Senha: ")
        if (readln() == senhaCorreta) {
            println("✅ Bem-vindo ao Hotel ${HotelState.nomeHotel}, $nomeUsuario. É um imenso prazer!")
            inicio()
            return
        } else {
            tentativas++
            println("❌ Senha incorreta. Tentativa $tentativas de 3.")
        }
    }

    println("🚫 Acesso bloqueado.")
}

// --- 📑 MENU ---
fun inicio() {
    var continuar = true

    while (continuar) {
        println("\n--- 🏁 MENU OPERACIONAL - ${HotelState.nomeHotel} ---")
        println("1. Clientes | 2. Funcionários | 3. Quartos | 4. Eventos")
        println("5. Salão | 6. Check-out | 7. Abastecimento | 8. Ar")
        println("9. Pesquisar | 10. Relatório | 11. Sair")

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
                println("👋 Até logo, $nomeUsuario")
                continuar = false
            }
            else -> println("❌ Opção inválida")
        }
    }
}

// --- 🛌 QUARTOS ---
fun reservaDeQuartos() {
    print("Diária: ")
    val valorDiaria = readln().toDoubleOrNull() ?: 0.0

    print("Dias: ")
    val dias = readln().toIntOrNull() ?: 0

    if (valorDiaria <= 0 || dias !in 1..30) {
        println("❌ inválido")
        return
    }

    print("Nome hóspede: ")
    val nomeH = readln()

    val fator = when (readln().uppercase()) {
        "E" -> 1.35
        "L" -> 1.65
        else -> 1.0
    }

    for (i in 0 until 20) {
        val status = if (HotelState.quartos[i] == "Livre") "L" else "O"
        print("[$status] ")
        if ((i + 1) % 5 == 0) println()
    }

    print("\nQuarto (1-20): ")
    val n = readln().toIntOrNull() ?: 0

    if (n !in 1..20 || HotelState.quartos[n - 1] != "Livre") {
        println("❌ inválido")
        return
    }

    val total = (valorDiaria * dias * fator) * 1.10

    println("Total: R$ %.2f".format(total))
    print("Confirmar? (S/N): ")

    if (readln().uppercase() == "S") {
        HotelState.quartos[n - 1] = "Ocupado ($nomeH)"
        HotelState.receitaHospedagem += total
        println("✅ reservado")
    }
}

// --- 🎭 EVENTOS ---
fun menuReservas() {
    print("Convidados: ")
    val convidados = readln().toIntOrNull() ?: -1

    if (convidados <= 0 || convidados > 350) {
        println("❌ inválido")
        return
    }

    val auditorio = if (convidados <= 220) "Laranja" else "Colorado"

    print("Dia: ")
    val dia = readln().lowercase()

    print("Hora: ")
    val hora = readln().toIntOrNull() ?: 0

    print("Duração: ")
    val duracao = readln().toIntOrNull() ?: 1

    val limite = if (dia == "sabado" || dia == "domingo") 15 else 23

    if (hora < 7 || hora + duracao > limite) {
        println("❌ horário inválido")
        return
    }

    val garcons = ceil(convidados / 12.0).toInt() + floor(duracao / 2.0).toInt()
    val custoGarcons = garcons * duracao * 10.50

    val buffet =
        (convidados * 0.2 * 0.8) +
                (convidados * 0.5 * 0.4) +
                (ceil((convidados * 7) / 100.0) * 34.0)

    val total = custoGarcons + buffet

    println("Total evento: R$ %.2f".format(total))
    print("Confirmar? (S/N): ")

    if (readln().uppercase() == "S") {
        HotelState.receitaEventos += total
        HotelState.totalEventosConfirmados++
        println("✅ evento ok")
    }
}

// --- 🎊 SALÃO ---
fun reservaSalaoFestas() {
    print("Email: ")
    val email = readln().trim()

    val cliente = HotelState.listaClientes.find {
        it.email.equals(email, ignoreCase = true)
    }

    if (cliente == null) {
        println("❌ não encontrado")
        return
    }

    print("Data: ")
    val data = readln()

    if (HotelState.datasOcupadasSalao.contains(data)) {
        println("❌ ocupado")
        return
    }

    HotelState.datasOcupadasSalao.add(data)

    println("✅ salão reservado para ${cliente.nome}")
}

// --- 🧾 RELATÓRIO ---
fun exibirReceita() {

    val ocupados = HotelState.quartos.count { it != "Livre" }
    val taxa = (ocupados.toDouble() / 20.0) * 100

    println("\n--- RELATÓRIO ${HotelState.nomeHotel} ---")
    println("Quartos ocupados: $ocupados")
    println("Taxa ocupação: %.1f%%".format(taxa))
    println("Clientes: ${HotelState.listaClientes.size}")
    println("Eventos: ${HotelState.totalEventosConfirmados}")
    println("Hospedagem: ${HotelState.receitaHospedagem}")
    println("Eventos receita: ${HotelState.receitaEventos}")
    println("TOTAL: ${HotelState.receitaHospedagem + HotelState.receitaEventos}")
}

// --- AUX ---
fun realizarCheckOut() {
    print("Quarto: ")
    val n = readln().toIntOrNull() ?: 0

    if (n in 1..20 && HotelState.quartos[n - 1] != "Livre") {
        HotelState.quartos[n - 1] = "Livre"
        println("✅ check-out")
    } else println("❌ inválido")
}

fun pesquisarHospede() {
    print("Nome: ")
    val busca = readln().lowercase()

    val encontrados = HotelState.listaClientes.filter {
        it.nome.lowercase().startsWith(busca)
    }

    if (encontrados.isEmpty()) println("❌ nada")
    else encontrados.forEach { println("${it.nome} - ${it.email}") }
}

fun cadastrarCliente() {
    print("Nome: ")
    val nome = readln()

    print("Email: ")
    val email = readln()

    HotelState.listaClientes.add(
        model.Cliente(nome, email)
    )

    println("✅ cadastrado")
}

fun cadastrarFuncionario() {
    println("⚠️ via controller")
}

fun abastecimento() {
    print("Wayne A: "); val wa = readln().toDoubleOrNull() ?: 0.0
    print("Wayne G: "); val wg = readln().toDoubleOrNull() ?: 0.0
    print("Stark A: "); val sa = readln().toDoubleOrNull() ?: 0.0
    print("Stark G: "); val sg = readln().toDoubleOrNull() ?: 0.0

    val w = if (wa <= wg * 0.7) wa else wg
    val s = if (sa <= sg * 0.7) sa else sg

    if (w < s) println("Wayne mais barato")
    else println("Stark mais barato")
}