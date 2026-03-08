package es.ies.ejercicios.u6.ej65.dip

import es.ies.ejercicios.u6.ej64.InformeCsv
import es.ies.ejercicios.u6.ej64.InformeMarkdown
import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Abstracción de un generador de informes
 */
interface GeneradorInforme {
    fun generar(titulo: String, items: List<Resumible>): String
}

/**
 * Implementación concreta: CSV
 */
class GeneradorCsv : GeneradorInforme {
    private val informeCsv = InformeCsv()

    override fun generar(titulo: String, items: List<Resumible>): String =
        informeCsv.generar(titulo, items)
}

/**
 * Implementación concreta: Markdown
 */
class GeneradorMarkdown : GeneradorInforme {
    private val informeMarkdown = InformeMarkdown()

    override fun generar(titulo: String, items: List<Resumible>): String =
        informeMarkdown.generar(titulo, items)
}

/**
 * Módulo de alto nivel: controlador de informes
 * Ahora depende solo de la abstracción GeneradorInforme
 */
class ControladorInformes(private val generador: GeneradorInforme) {

    fun imprimirListado(items: List<Resumible>) {
        val salida = generador.generar("Listado DIP", items)
        println(salida)
    }
}

fun main() {
    val items = listOf<Resumible>(
        Persona("Ana", 20),
        Persona("Luis", 19)
    )
    
    val controladorCsv = ControladorInformes(GeneradorCsv())
    println("[DIP] Informe CSV")
    controladorCsv.imprimirListado(items)

    val controladorMarkdown = ControladorInformes(GeneradorMarkdown())
    println("\n[DIP] Informe Markdown")
    controladorMarkdown.imprimirListado(items)
}