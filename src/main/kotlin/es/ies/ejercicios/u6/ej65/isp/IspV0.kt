package es.ies.ejercicios.u6.ej65.isp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * Interfaces segregadas por capacidades
 */

// Solo lectura
interface BuscadorPersonas {
    fun buscar(nombre: String): Persona?
}

// Escritura
interface GuardadorPersonas {
    fun guardar(persona: Persona)
}

// Exportación
interface ExportadorPersonas {
    fun exportarCsv(): String
}

// Borrado
interface LimpiadorPersonas {
    fun borrarTodo()
}

/**
 * Repositorio en memoria que implementa todas las capacidades
 */
class RepositorioMemoria : BuscadorPersonas, GuardadorPersonas, ExportadorPersonas, LimpiadorPersonas {
    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? = map[nombre]

    override fun exportarCsv(): String =
        buildString {
            appendLine("nombre,edad")
            for (p in map.values) appendLine("${p.nombre},${p.edad}")
        }

    override fun borrarTodo() {
        map.clear()
    }
}

/**
 * Cliente que solo necesita buscar
 */
class BuscadorPersonasCliente(private val repo: BuscadorPersonas) {
    fun buscar(nombre: String): Persona? = repo.buscar(nombre)
}

/**
 * Cliente que necesita guardar y buscar
 */
class GestorPersonasCliente(
    private val buscador: BuscadorPersonas,
    private val guardador: GuardadorPersonas
) {
    fun agregarYBuscar(persona: Persona): Persona? {
        guardador.guardar(persona)
        return buscador.buscar(persona.nombre)
    }
}

fun main() {
    val repo = RepositorioMemoria()

    // Cliente que solo busca
    val buscadorCliente = BuscadorPersonasCliente(repo)
    repo.guardar(Persona("Ana", 20))
    println("Buscar Ana -> ${buscadorCliente.buscar("Ana")?.resumen()}")

    // Cliente que guarda y busca
    val gestorCliente = GestorPersonasCliente(repo, repo)
    val resultado = gestorCliente.agregarYBuscar(Persona("Luis", 19))
    println("Buscar Luis -> ${resultado?.resumen()}")
}