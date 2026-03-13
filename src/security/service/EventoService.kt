package security.service


class EventoService {

    // Função para validar a data/evento
    fun validarEvento(escolha: String): String {
        return if (escolha == "Aniversario do Corinthians" || escolha == "Grande Evento") {
            "Evento $escolha selecionado!"
        } else {
            "Evento inválido! Continuando sem evento especial."
        }
    }

    // Função que retorna as marcas (Data) em vez de apenas imprimir
    fun obterMarcasBebida(opcao: String): List<String> {
        return when (opcao) {
            "1" -> listOf("Bavária", "Budweiser", "Heineken", "Itaipava", "Beck's", "Corona", "Skol")
            "2" -> listOf("Coca-Cola", "Coca-Cola Zero", "Guaraná", "Fanta Laranja", "Fanta Uva", "Sprite")
            "3" -> listOf("Água Mineral", "Água com Gás")
            else -> emptyList()
        }
    }

    // A lógica para o Churrasco das crianças
    fun filtrarOpcoesKids(opcao: String): Boolean {
        // Se for "1" (Cerveja), retorna falso. Se for "2" ou "3", verdadeiro.
        return opcao == "2" || opcao == "3"
    }
}