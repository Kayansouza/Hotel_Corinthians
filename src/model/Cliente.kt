package model

// classe cliente, que representa um cliente do hotel. um blueprint. Ela possui quatro propriedades: nome, cpf, email e telefone, que são do tipo String. O construtor da classe recebe esses quatro parâmetros e os atribui às propriedades correspondentes.

data class Cliente(
    var nome: String,
    var cpf: String,
    var email: String,
    var telefone: String,
    var senha: String,
    val idade: Int
) {

}