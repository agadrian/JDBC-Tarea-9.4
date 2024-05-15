package service

import dao.IBookDAO
import entity.Book
import java.util.*


/**
 * Implementación de [IBookService] que proporciona métodos para operaciones CRUD
 * en libros utilizando un objeto que implementa [IBookDAO].
 *
 * @property bookDAO Objeto que implementa la interfaz [IBookDAO] para acceder a los datos de los libros.
 */
class BookServiceImpl(private val bookDAO: IBookDAO) : IBookService {

    override fun create(book: Book): Book? {
        return bookDAO.create(book)
    }

    override fun getById(id: UUID): Book? {
        return bookDAO.getById(id)
    }

    override fun update(book: Book): Book? {
        return bookDAO.update(book)
    }

    override fun delete(id: UUID): Boolean {
        return bookDAO.delete(id)
    }

    override fun getAll(): List<Book>? {
        return bookDAO.getAll()
    }

    override fun deleteAll(): Boolean {
        return bookDAO.deleteAll()
    }

}
