package security.service
import kotlin.math.ceil
import kotlin.math.floor

class EventoService {
    fun calcularGarcons(conv: Int, dur: Int): Int = ceil(conv / 12.0).toInt() + floor(dur / 2.0).toInt()

    fun calcularBuffet(conv: Int): Triple<Double, Double, Int> =
        Triple(conv * 0.2, conv * 0.5, conv * 7)

    fun calcularCusto(garcons: Int, dur: Int, cafe: Double, agua: Double, salgados: Int): Double {
        return (garcons * dur * 10.50) + (cafe * 0.80) + (agua * 0.40) + (ceil(salgados / 100.0) * 34.0)
    }
}