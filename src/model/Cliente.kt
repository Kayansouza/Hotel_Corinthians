package model

// classe cliente, que representa um cliente do hotel. um blueprint. Ela possui quatro propriedades: nome, cpf, email e telefone, que são do tipo String. O construtor da classe recebe esses quatro parâmetros e os atribui às propriedades correspondentes.

<<<<<<< HEAD
data class Cliente(
    var nome: String,
    var cpf: String,
    var email: String,
    var telefone: String,
    var senha: String,
    val idade: Int
) {
=======
data class Cliente( var nome: String,
                    var cpf: String,
                    var email: String,
                    var telefone: String,
                    var senha: String) {
>>>>>>> 6b72fab80b4e0fc80333f2b06da299865bf51a29

}