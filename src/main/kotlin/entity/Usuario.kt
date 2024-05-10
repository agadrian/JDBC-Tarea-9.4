package entity



sealed class Usuario{
    data class Visitante(val id: String, val nombre: String): Usuario()
    data class Estudiante(val id: String, val nombre: String, val carrera: String): Usuario()
    data class Profesor(val id:String, val nombre: String, val departamento: String): Usuario()
}

/**
 * Determina si un usuario puede tomar prestado un libro y devuelve un mensaje indicando la capacidad de préstamo.
 * @param usuario El usuario que desea tomar prestado el libro.
 * @param libro El libro que se desea tomar prestado.
 * @return Un mensaje indicando si el usuario puede tomar prestado el libro y por cuánto tiempo.
 */
fun puedeTomarPrestado(usuario: Usuario, libro: Libro): String{
    return when (usuario){
        is Usuario.Estudiante -> "${usuario.nombre}, eres estudiante, puedes tomar prestado 1 semana el libro: ${libro.titulo}"
        is Usuario.Profesor -> "${usuario.nombre}, eres profesor, puedes tomar prestado 3 semanas el libro: ${libro.titulo}"
        is Usuario.Visitante -> "${usuario.nombre}, eres un visitante, no puedes tomar prestado el libro: ${libro.titulo}"
    }
}