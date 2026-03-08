package es.ies.ejercicios.u6.ej64

// Este fichero contiene ejemplos de:
// - herencia (6.1)
// - clase abstracta e interfaces (6.2)
// - constructores e init en herencia (6.3)
//
// Tu tarea (6.4) es:
// - Entender el código y su relación entre clases e interfaces.
// - Mejorar la documentación KDoc donde sea necesario.
// - Añadir comentarios solo cuando aporten valor.
// - Eliminar comentarios innecesarios o redundantes.

/**
 * Representa cualquier elemento capaz de generar
 * un resumen de sí mismo.
 */
interface Resumible {

    /**
     * Devuelve un resumen en texto del objeto.
     *
     * @return texto resumido del elemento
     */
    fun resumen(): String
}

/**
 * Clase abstracta que define una plantilla para generar informes.
 *
 * Implementa el patrón Template Method: el método [generar]
 * define el flujo general del informe mientras que las subclases
 * personalizan el formato del contenido.
 */

abstract class PlantillaInforme : Resumible {

    /**
     * Genera un informe completo en formato texto.
     *
     * @param titulo título del informe
     * @param items lista de elementos resumibles
     * @return informe formateado
     */

    fun generar(titulo: String, items: List<Resumible>): String {

        val sb = StringBuilder()

        sb.appendLine(cabecera(titulo))

        for (item in items) {
            sb.appendLine(formatearItem(item))
        }

        sb.appendLine(pie())

        return sb.toString()
    }

    /**
     * Devuelve la cabecera del informe.
     */

    protected open fun cabecera(titulo: String): String = titulo

    /**
     * Formatea un elemento individual del informe.
     */

    protected abstract fun formatearItem(item: Resumible): String

    /**
     * Devuelve el pie del informe.
     */

    protected open fun pie(): String = "-- fin --"

    override fun resumen(): String = "PlantillaInforme"
}

/**
 * Genera informes en formato Markdown.
 */

class InformeMarkdown : PlantillaInforme() {

    override fun cabecera(titulo: String): String = "# $titulo"

    override fun formatearItem(item: Resumible): String = "- ${item.resumen()}"
}

/**
 * Genera informes en formato CSV.
 */

class InformeCsv : PlantillaInforme() {

    override fun cabecera(titulo: String): String = "titulo,$titulo\nitem"

    override fun formatearItem(item: Resumible): String = item.resumen().replace(",", ";")
}

/**
 * Representa una persona con nombre y edad.
 *
 * @property nombre nombre de la persona
 * @property edad edad de la persona
 */

open class Persona(
    val nombre: String,
    val edad: Int,
) : Resumible {

    init {
        println("[Persona:init] nombre=$nombre edad=$edad")
    }

    /**
     * Constructor secundario que crea una persona
     * con edad por defecto igual a 0.
     */

    constructor(nombre: String) : this(nombre, edad = 0) {
        println("[Persona:secondary] constructor(nombre)")
    }

    /**
     * Devuelve un resumen con nombre y edad.
     */

    override fun resumen(): String = "$nombre ($edad)"
}

/**
 * Representa un alumno que hereda de [Persona]
 * y añade el curso en el que está matriculado.
 */

class Alumno : Persona {

    val curso: String

    /**
     * Constructor principal del alumno.
     */

    constructor(nombre: String, edad: Int, curso: String) : super(nombre, edad) {
        this.curso = curso
        println("[Alumno:secondary] nombre=$nombre edad=$edad curso=$curso")
    }

    /**
     * Constructor secundario con edad por defecto.
     */

    constructor(nombre: String, curso: String) :
            this(nombre, edad = 0, curso = curso) {
        println("[Alumno:secondary] constructor(nombre, curso)")
    }

    override fun resumen(): String = "Alumno: ${super.resumen()} curso=$curso"
}

/**
 * Registro simple de personas ordenadas por nombre.
 *
 * Para evitar duplicados, el nombre se normaliza eliminando
 * espacios y convirtiendo a minúsculas.
 */

class RegistroPersonas {

    private val personasPorNombre = mutableMapOf<String, Persona>()

    /**
     * Registra una persona en el sistema.
     */

    fun registrar(persona: Persona) {
        val clave = normalizarNombre(persona.nombre)
        personasPorNombre[clave] = persona
    }

    /**
     * Busca una persona por nombre.
     */

    fun buscar(nombre: String): Persona? =
        personasPorNombre[normalizarNombre(nombre)]

    /**
     * Normaliza un nombre eliminando espacios
     * y convirtiéndolo a minúsculas.
     */

    private fun normalizarNombre(nombre: String): String {
        return nombre.trim().lowercase()
    }
}
