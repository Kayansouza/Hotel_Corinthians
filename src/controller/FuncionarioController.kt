package controller

import security.service.Funcionario

// O FuncionarioController herda do UsuarioController
class FuncionarioController : UsuarioController() {

    // Regra específica para o código de funcionário
    fun validarCodigoFuncionario(codigo: String): Boolean {
        // Exemplo: todos devem ter o código "BANDO2026"
        return codigo == "BANDO2026"
    }

    // Você pode sobrescrever ou criar um novo validador específico para eles
    fun processarCadastroFuncionario(email: String, ano: Int?, codigo: String, lista: List<Funcionario>): Boolean {
        val dadosBasicosValidos = super.processarCadastro(email, ano, emptyList()) // Chama a lógica do pai
        val codigoValido = validarCodigoFuncionario(codigo)

        return dadosBasicosValidos && codigoValido
    }
}