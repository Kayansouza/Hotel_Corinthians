package model

data class Cliente(
    var nome: String,
    var email: String = "",
    var telefone: String = "",
    var cpf: String = "000.000.000-00",
    var senhaHash: String = ""
) {

}