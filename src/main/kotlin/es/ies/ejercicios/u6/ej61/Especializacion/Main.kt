package es.ies.ejercicios.u6.ej61.Especializacion

fun main() {

    val animal1: Animal = Perro("Rocky")
    val animal2: Animal = Gato("Misu")

    animal1.hacerSonido()
    animal1.moverse()

    animal2.hacerSonido()
    animal2.moverse()
}