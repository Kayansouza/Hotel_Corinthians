
import model.Cliente
import model.Usuario
import model.funcionario
import repository.UsuarioRepository

fun main() {
    inicio()

    }

fun inicio () {


 // Adicionado novas funcionabilidades para o cliente, como consultar a reserva, cancelar a reserva, fazer uma nova reserva e sair do hotel. O cliente pode escolher uma dessas opções para realizar a ação desejada. O sistema irá processar a escolha do cliente e executar a função correspondente à opção selecionada.
    println("Escolha uma opção:")

    val escolha = readln().toInt()
    when (escolha) {
        1 -> cadastrarcliente()
        2 -> EntrarComSenha()
        3 -> AbastecimentoDeAutomoveis()
        4 -> ConsultarMinhaReserva()
        5 -> CancelarMinhaReserva()
        6-> FazerUmaNovaReserva()
        7 -> sairDoHotel()
        else -> erro()
    }

    // Criando o cadastro do cliente, onde o usuário irá informar os dados para se cadastrar no hotel. O cadastro é feito através da função inicio(), onde o usuário é solicitado a informar seu nome, cpf, email, telefone e senha. Esses dados são armazenados em variáveis locais e depois utilizados para criar um objeto do tipo cliente, utilizando o construtor da classe cliente. O objeto criado é armazenado na variável pessoa.

    println("Informe seu nome para se cadastrar no hotel:")
    var nome = readln()

    println("Digite o CPF:")
    var cpf = readln()

    println("Por favor, informe o email:")
    var email = readln()

    println("Por favor, informe o telefone:")
    var telefone = readln()

    println("Por favor, informe a senha:")
    var senha = readln()

    // instanciando um objeto do tipo cliente, utilizando o construtor da classe cliente. O construtor recebe os parâmetros nome, cpf, email e telefone e os atribui às propriedades correspondentes da classe cliente. O objeto criado é armazenado na variável pessoa.

    var pessoa = Cliente(nome, cpf, email, telefone,senha)
    var user = Usuario(nome, cpf, email, telefone,senha)
    var roxo = funcionario()

}

fun   cadastrarcliente (){

    val nome = ""
    println("Seja bem-vindo, $nome!")
    println("Por favor,  informe seu nome:")

    if (nome.isNotEmpty()) {
        var nome = readln()
        println("Localizamos seu cadastro, seja bem-vindo de volta, $nome!")

    } else {
        println ("Não encontramos seu cadastro, por favor, informe os dados para se cadastrar no hotel. $nome")
        inicio()
    }
}

fun  EntrarComSenha (){
    println("Obá, Muito bom ter ver novamente por aqui! Por favor, informe seu email e senha para acessar sua conta. \n")
    val email = readln()
    val senha = readln()

    if (email.isNotEmpty() && senha.isEmpty()) {
        println("Acesso negado. Por favor, informe uma senha válida.")

    }
    else if (email.isEmpty() && senha.isEmpty()) {
        println("Acesso negado. Por favor, informe um email e senha válidos.")
    }

    else if (email.isEmpty() && senha.isEmpty()) {
        println("Infelizmente o seu acesso foi negado. Por favor, informe um email válido.")
    }

     else if (email.isEmpty() && senha.isNotEmpty()) {
        println("Infelizmente o seu acesso foi negado. Por favor, informe um email válido.")
        }

        else if (email.isNotEmpty() && senha.isNotEmpty()) {
            println("Acesso concedido. Bem-vindo ao Bando De Loucos, $email!")
        }

    }


fun AbastecimentoDeAutomoveis() {
    println("Olá, seja bem-vindo ao serviço de abastecimento. Eu me chamo Milena e estou aqui para ajudar você a abastecer seu veículo. Por favor, informe o tipo de combustível que deseja abastecer: \n")
    println( "1- Gasolina \n 2- Etanol \n 3- Diesel \n 4- GNV \n 5- Flex \n")

    val formatoPagamento = readln()
    println("Por favor, informe a forma de pagamento: \n")

    println(" 1 - Cartão de Crédito \n 2 - Cartão de Débito \n 3 - Dinheiro \n 4 - Pix \n")
    val formaPagamento = readln().toInt()

    when (formaPagamento) {
        1 -> println("Você escolheu pagar com cartão de crédito. Por favor, insira os dados do seu cartão para concluir a transação.")
        2 -> println("Você escolheu pagar com cartão de débito. Por favor, insira os dados do seu cartão para concluir a transação.")
        3 -> println("Você escolheu pagar com dinheiro. Por favor, dirija-se ao caixa para concluir a transação.")
        4 -> println("Você escolheu pagar com Pix. Por favor, escaneie o código QR para concluir a transação.")
        else -> println("Opção de pagamento inválida. Por favor, escolha uma opção válida.")
    }
}

// Cliente terá a opção de consultar a sua reserva. Basta informa o número da reserva

fun ConsultarMinhaReserva() {
    println("Por favor, informe o número da sua reserva para que possamos localizar suas informações. \n")

    println("Número da reserva: ")
    val numeroReserva = readln()
    println("Sua reserva foi localizada com sucesso! \n")

    println("O seu quarto está reservado para a data de entrada: 15/03/2026 e data de saída: 25/3/2026. \n")
    println("O número do seu quarto é: 101. \n")
    println("Horario de check-in: 14:00. \n")
    println("Horario de check-out: 12:00. \n")
    println("Você ficará hospedado com 4 pessoas. Sendo 2 Adultos e 2 crinaças\n")



}

// Cliente terá a opção de fazer uma nova reserva. O cliente irá informar a data de entrada, data de saída, número de pessoas e o tipo de quarto que deseja reservar. O sistema irá verificar a disponibilidade do quarto e confirmar a reserva para o cliente.
fun FazerUmaNovaReserva() {

    println("Olá, Seja bem-vindo ao serviço de reservas do Hotel Bando De Loucos!. Por favor, poderia informar alguns dados, para fazermos a sua reserva? \n")

    println("Quantas pessoas irão se hospedar? \n")
    val numeroPessoas = readln()

    println("Qual é a data de entrada? \n")
    val dataEntrada = readln()

    println("Qual é a data de saida? \n")
    val dataSainda = readln()
}

 // Cliente terá a opção de cancelar a sua reserva. O cliente irá informar o número da reserva que deseja cancelar e o sistema irá confirmar o cancelamento da reserva para o cliente.
fun CancelarMinhaReserva () {
    println("Por favor, me informe o numero da reseva que deseja cancelar: \n")
    val numeroReserva = readln()

    println("Seu número de reserva foi cancelado com sucesso! \n")
}

 // Cliente terá a opção de sair do hotel. O cliente irá informar que deseja sair do hotel e o sistema irá confirmar a saída do hotel para o cliente.
fun sairDoHotel(){

    println("Obrigado por escolher o Hotel Bando De Loucos! Esperamos vê-lo novamente em breva. Ótimo dia! \n")
    println("Saindo do hotel.(..")



}

fun erro() {
    println("Opção inválida. Por favor, escolha uma opção válida.")
}
