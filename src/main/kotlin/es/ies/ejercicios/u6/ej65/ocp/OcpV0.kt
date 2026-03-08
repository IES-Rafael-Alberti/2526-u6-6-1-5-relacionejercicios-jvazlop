package es.ies.ejercicios.u6.ej65.ocp

import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Interfaz para cualquier generador de informe
 */
interface GeneradorInforme {
    fun generar(titulo: String, items: List<Resumible>): String
}

/**
 * Implementacion CSV
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
 * Implementacion Markdown
 */
class GeneradorMarkdown : GeneradorInforme {

    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("# $titulo")
            for (item in items) appendLine("- ${item.resumen()}")
        }
}

/**
 * Ejemplo de nuevo formato (sin modificar el generador)
 */
class GeneradorHtml : GeneradorInforme {

    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("<h1>$titulo</h1>")
            appendLine("<ul>")
            for (item in items) appendLine("<li>${item.resumen()}</li>")
            appendLine("</ul>")
        }
}

/**
 * Clase que usa cualquier generador
 */
class ServicioInforme(private val generador: GeneradorInforme) {

    fun ejecutar(titulo: String, items: List<Resumible>): String {
        return generador.generar(titulo, items)
    }
}

fun main() {

    val items = listOf<Resumible>(
        object : Resumible {
            override fun resumen(): String = "Elemento A"
        },
        object : Resumible {
            override fun resumen(): String = "Elemento B"
        },
    )

    // Aquí puedo decidir el formato
    // val generador = GeneradorCsv()
    // val generador = GeneradorHtml()
    val generador = GeneradorMarkdown()

    val servicio = ServicioInforme(generador)

    println(servicio.ejecutar("Demo OCP", items))
}