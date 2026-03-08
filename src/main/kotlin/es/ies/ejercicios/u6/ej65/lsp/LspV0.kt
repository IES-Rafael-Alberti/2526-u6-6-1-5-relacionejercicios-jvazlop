package es.ies.ejercicios.u6.ej65.lsp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * Capacidad de lectura
 */
interface BuscadorPersonas {
    fun buscar(nombre: String): Persona?
}

/**
 * Capacidad de escritura
 */
interface GuardadorPersonas {
    fun guardar(persona: Persona)
}

/**
 * Repositorio completo (lectura + escritura)
 */
class RepositorioPersonas : BuscadorPersonas, GuardadorPersonas {

    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? = map[nombre]
}

/**
 * Repositorio solo lectura
 */
class RepositorioSoloLectura(
    private val datos: Map<String, Persona>
) : BuscadorPersonas {

    override fun buscar(nombre: String): Persona? = datos[nombre]
}

/**
 * Cliente que necesita leer y escribir
 */
fun clienteCompleto(
    repo: BuscadorPersonas,
    guardador: GuardadorPersonas
) {
    guardador.guardar(Persona("Ana", 20))
    println("Buscar Ana -> ${repo.buscar("Ana")?.resumen()}")
}

/**
 * Cliente que solo necesita leer
 */
fun clienteSoloLectura(repo: BuscadorPersonas) {
    println("Buscar Ana -> ${repo.buscar("Ana")?.resumen()}")
}

fun main() {

    println("[ISP/LSP:v1] Repositorio completo")
    val repo = RepositorioPersonas()
    clienteCompleto(repo, repo)

    println("\n[ISP/LSP:v1] Repositorio solo lectura")
    val datos = mapOf("Ana" to Persona("Ana", 20))
    val repoLectura = RepositorioSoloLectura(datos)

    clienteSoloLectura(repoLectura)
}