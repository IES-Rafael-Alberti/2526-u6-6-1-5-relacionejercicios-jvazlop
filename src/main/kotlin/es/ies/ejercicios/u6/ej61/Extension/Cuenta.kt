package es.ies.ejercicios.u6.ej61.Extension

open class Cuenta(var saldo: Double) {

    fun ingresar(cantidad: Double) {
        saldo += cantidad
        println("Ingresados $cantidad€. Saldo actual: $saldo€")
    }

    fun retirar(cantidad: Double) {
        saldo -= cantidad
        println("Retirados $cantidad€. Saldo actual: $saldo€")
    }
}

class CuentaAhorro(saldo: Double) : Cuenta(saldo) {

    fun aplicarInteres() {
        saldo *= 1.05
        println("Interés aplicado. Nuevo saldo: $saldo€")
    }
}

class CuentaPremium(saldo: Double) : Cuenta(saldo) {

    fun retirarSinComision(cantidad: Double) {
        saldo -= cantidad
        println("Retirada sin comisión. Saldo actual: $saldo€")
    }
}