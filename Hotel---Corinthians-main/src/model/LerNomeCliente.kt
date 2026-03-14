import model.Cliente

fun LerNomeCliente(): Cliente {
    println("Digite o nome do cliente:")
    val nome = readln()

    println("Digite o CPF do cliente:")
    val cpf = readln()

    println("Digite o email do cliente:")
    val email = readln()

    println("Digite o telefone do cliente:")
    val telefone = readln()

    println("Digite a sua senha:")
    val senha = readln()

    println("Cliente cadastrado com sucesso!\n")

    return Cliente(
        nome = nome,
        cpf = cpf,
        email = email,
        telefone = telefone,
        senha = senha
    )
}

fun main() {
    val cliente = LerNomeCliente()
    println("Seja bem-vindo, ${cliente.nome}!")
}