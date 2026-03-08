package es.ies.ejercicios.u6.ej65.srp

import es.ies.ejercicios.u6.ej64.Alumno
import es.ies.ejercicios.u6.ej64.InformeMarkdown
import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.RegistroPersonas
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Clase encargada de preparar los datos
 */
class PreparadorDatos {

    fun obtenerItems(): List<Resumible> {
        return listOf(
            Persona(" Ana ", 20),
            Alumno("Luis", 19, "1DAM"),
            Persona("Marta", 18),
        )
    }
}

/**
 * Clase encargada de registrar personas
 */
class RegistroService {

    fun registrar(items: List<Resumible>): RegistroPersonas {
        val registro = RegistroPersonas()

        for (item in items) {
            if (item is Persona) {
                registro.registrar(item)
            }
        }

        return registro
    }
}

/**
 * Clase encargada de generar el informe
 */
class GeneradorInforme {

    fun generar(items: List<Resumible>): String {
        val informe = InformeMarkdown()
        return informe.generar("Listado", items)
    }
}

/**
 * Logger simple para la consola
 */
class Logger {

    fun log(mensaje: String) {
        println(mensaje)
    }
}

/**
 * Servicio principal que coordina
 */
class InformeAppService {

    fun ejecutar() {

        val logger = Logger()
        val preparador = PreparadorDatos()
        val registroService = RegistroService()
        val generador = GeneradorInforme()

        logger.log("[SRP:v1] Preparando datos...")
        val items = preparador.obtenerItems()

        logger.log("[SRP:v1] Registrando personas...")
        val registro = registroService.registrar(items)

        logger.log("[SRP:v1] Generando informe Markdown...")
        val salida = generador.generar(items)

        logger.log("[SRP:v1] Resultado:")
        println(salida)

        logger.log("[SRP:v1] Buscar 'ana' -> ${registro.buscar("ana")?.resumen()}")
    }
}

fun main() {
    InformeAppService().ejecutar()
}

