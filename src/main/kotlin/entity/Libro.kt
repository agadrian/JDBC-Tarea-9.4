package entity


/**
 * Clase que representa un libro en la biblioteca.
 * @property titulo El título del libro.
 * @property autor El autor del libro.
 * @property anioPublicacion El año de publicación del libro.
 */
data class Libro(val titulo: String, val autor: String, val anioPublicacion: Int)
