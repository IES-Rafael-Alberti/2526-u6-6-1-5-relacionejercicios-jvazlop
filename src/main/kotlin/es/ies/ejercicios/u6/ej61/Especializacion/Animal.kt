package es.ies.ejercicios.u6.ej61.Especializacion

open class Animal(val nombre: String) {

    open fun hacerSonido() {
        println("El animal hace un sonido")
    }

    fun moverse() {
        println("$nombre se está moviendo")
    }
}

class Perro(nombre: String) : Animal(nombre) {
    override fun hacerSonido() {
        println("$nombre dice: Guau!")
    }
}

class Gato(nombre: String) : Animal(nombre) {
    override fun hacerSonido() {
        println("$nombre dice: Miau!")
    }
}