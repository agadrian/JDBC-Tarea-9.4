package entity

import java.util.UUID


/**
 * Clase que representa un libro en la biblioteca.
 * @property id La id de tipo UUID
 * @property title El título del libro.
 * @property autor El autor del libro.
 * @property yearPublication El año de publicación del libro.
 */
data class Book(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val autor: String,
    val yearPublication: Int)
