package security.service

class ReservaService {

    fun calcularValorTotal(valorDiaria: Double, dias: Int): Double {

        if (valorDiaria <= 0 || dias <=0 || dias > 30){
            println("Valor definido")
            return 0.0
        }
        return valorDiaria * dias
    }



}