package model


class funcionario {
        var nome: String = ""
        var cpf: String = ""
        var cargo: String = ""
        var salario: Double = 0.0


    fun cadastrarfuncionario() {
        println("Digite o nome do funcionário:")
        nome = readln()
        println("Digite o CPF do funcionário:")
        cpf = readln()
        println("Digite o cargo do funcionário:")
        cargo = readln()
        println("Digite o salário do funcionário:")
        salario = readln().toDoubleOrNull() ?: 0.0
    }
}

