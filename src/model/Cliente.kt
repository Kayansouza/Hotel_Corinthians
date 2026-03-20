package model

// classe cliente, que representa um cliente do hotel. um blueprint. Ela possui quatro propriedades: nome, cpf, email e telefone, que são do tipo String. O construtor da classe recebe esses quatro parâmetros e os atribui às propriedades correspondentes.


// Em model/Cliente.kt
data class Cliente(
    val nome: String,
    val anoDeNascimento: Int,
    val email: String,
    val telefone: String,
    val cpf: String,
    val senhaHash: String,
    // O SEGREDO: Ela precisa ser uma MutableList e já começar criada
    val historico: MutableList<String> = mutableListOf()
)