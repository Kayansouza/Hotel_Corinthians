package model

enum class TipoGasolina(val nome: String) {
    GASOLINA("Gasolina"),
    ETANOL("Etanol"),
    ALCOOL("Álcool")
}

data class Abastecimento(
    val tipo: String,
    val valorLitro: Double
)
