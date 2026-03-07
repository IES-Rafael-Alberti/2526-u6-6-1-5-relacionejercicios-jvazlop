package es.ies.ejercicios.u6.ej63

/**
 * Ejercicio 6.3 — Incidencia de constructores en la herencia (RA7.c).
 *
 * Punto de partida: revisa `Figuras.kt` y completa lo indicado en `docs/ejercicios/6.3.md`.
 */
object Ej63

fun main() {

    println("Rectangulo")
    val r1 = Rectangulo("rojo", "miRect", 10, 20)

    println()
    val r2 = Rectangulo(5, 6)
    println()
    val r3 = Rectangulo(4) // cuadrado

    println("\nCirculo")
    val c1 = Circulo("azul", "miCirculo", 7)
    println()
    val c2 = Circulo(10)

    println("\nTriangulo")
    val t1 = Triangulo(6, 8)
    println()
    val t2 = Triangulo(5)
}