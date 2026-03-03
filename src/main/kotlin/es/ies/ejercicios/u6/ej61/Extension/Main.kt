package es.ies.ejercicios.u6.ej61.Extension

fun main() {

    val cuenta1: Cuenta = CuentaAhorro(1000.0)
    val cuenta2: Cuenta = CuentaPremium(2000.0)

    cuenta1.ingresar(200.0)
    cuenta2.retirar(300.0)

    println("Operaciones específicas:")

    if (cuenta1 is CuentaAhorro) {
        cuenta1.aplicarInteres()
    }

    if (cuenta2 is CuentaPremium) {
        cuenta2.retirarSinComision(500.0)
    }
}