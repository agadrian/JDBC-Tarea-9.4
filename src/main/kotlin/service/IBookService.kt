package service


import entity.Book
import java.util.*

interface IBookService {
    fun create(book: Book): Book?
    fun getById(id: UUID): Book?
    fun update(book: Book): Book?
    fun delete(id: UUID): Boolean
    fun getAll(): List<Book>?
    fun deleteAll(): Boolean
}