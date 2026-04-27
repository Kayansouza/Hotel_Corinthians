package controller

import model.Cliente

class HospedeController {
    private val limiteMaximo = 15

    fun gerenciarHospedes(lista: MutableList<Cliente>) {
        var voltar = false
        while (!voltar) {
            println("\n--- [Cadastro de Hóspedes] ---")
            println("1-Cadastrar | 2-Pesquisar Prefixo | 3-Listar | 4-Remover | 5-Voltar")
            print("Opção: ")

            when (readln().toIntOrNull()) {
                1 -> cadastrar(lista)
                2 -> pesquisarPrefixo(lista)
                3 -> listar(lista)
                4 -> remover(lista)
                5 -> voltar = true
                else -> println("❌ Opção inválida!")
            }
        }
    }

    private fun cadastrar(lista: MutableList<Cliente>) {
        if (lista.size >= limiteMaximo) {
            println("⚠️ Máximo de cadastros atingido ($limiteMaximo).")
            return
        }
        print("Nome: "); val nome = readln().trim()
        print("E-mail: "); val email = readln().trim()
        print("Telefone: "); val tel = readln().trim()

        val novo = Cliente(nome = nome, email = email, telefone = tel)
        lista.add(novo)
        println("✅ Hóspede $nome cadastrado com sucesso.")
    }

    private fun pesquisarPrefixo(lista: MutableList<Cliente>) {
        print("Digite o início do nome: ")
        val prefixo = readln().lowercase()
        val encontrados = lista.filter { it.nome.lowercase().startsWith(prefixo) }
        if (encontrados.isEmpty()) println("❌ Nenhum encontrado.")
        else encontrados.forEach { println("- ${it.nome}") }
    }

    private fun listar(lista: MutableList<Cliente>) {
        if (lista.isEmpty()) println("📭 Lista vazia.")
        else lista.forEachIndexed { i, c -> println("[${i + 1}] ${c.nome} (${c.email})") }
    }

    private fun remover(lista: MutableList<Cliente>) {
        listar(lista)
        if (lista.isNotEmpty()) {
            print("Número para remover: ")
            val idx = (readln().toIntOrNull() ?: 0) - 1
            if (idx in lista.indices) println("✅ ${lista.removeAt(idx).nome} removido.")
            else println("❌ Índice inválido.")
        }
    }
}