package dao


import entity.Book
import java.util.*

interface IBookDAO {
    fun create(book: Book): Book?
    fun getAll(): List<Book>?
    fun getById(id: UUID): Book?
    fun update(book: Book): Book?
    fun delete(id: UUID): Boolean
    fun deleteAll(): Boolean
}