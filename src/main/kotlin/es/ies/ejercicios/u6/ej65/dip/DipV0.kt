package es.ies.ejercicios.u6.ej65.dip

import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Abstracción de un generador de informes
 */
interface GeneradorInforme {
    fun generar(titulo: String, items: List<Resumible>): String
}

/**
 * Implementación concreta: Markdown
 */
class GeneradorMarkdown : GeneradorInforme {
    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("# $titulo")
            for (item in items) appendLine("- ${item.resumen()}")
        }
}

/**
 * Implementación concreta: CSV
 */
class GeneradorCsv : GeneradorInforme {
    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("titulo,$titulo")
            appendLine("item")
            for (item in items) appendLine(item.resumen().replace(",", ";"))
        }
}

/**
 * Módulo de alto nivel: servicio de informes
 */
class ServicioInforme(private val generador: GeneradorInforme) {

    fun ejecutar(titulo: String, items: List<Resumible>) {
        val salida = generador.generar(titulo, items)
        println(salida)
    }
}

fun main() {

    val items = listOf<Resumible>(
        object : Resumible { override fun resumen() = "Elemento A" },
        object : Resumible { override fun resumen() = "Elemento B" }
    )

    val servicioMarkdown = ServicioInforme(GeneradorMarkdown())
    println("[DIP] Informe Markdown")
    servicioMarkdown.ejecutar("Informe DIP", items)

    val servicioCsv = ServicioInforme(GeneradorCsv())
    println("\n[DIP] Informe CSV")
    servicioCsv.ejecutar("Informe DIP", items)
}