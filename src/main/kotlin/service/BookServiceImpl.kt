package service

import dao.IBookDAO
import entity.Book
import java.util.*


/**
 * Implementación de [IBookService] que proporciona métodos para operaciones CRUD
 * en libros utilizando un objeto que implementa [IBookDAO].
 *
 * @property userDao Objeto que implementa la interfaz [IBookDAO] para acceder a los datos de los libros.
 */
class BookServiceImpl(private val userDao: IBookDAO) : IBookService {

    override fun create(book: Book): Book? {
        return userDao.create(book)
    }

    override fun getById(id: UUID): Book? {
        return userDao.getById(id)
    }

    override fun update(book: Book): Book? {
        return userDao.update(book)
    }

    override fun delete(id: UUID): Boolean {
        return userDao.delete(id)
    }

    override fun getAll(): List<Book>? {
        return userDao.getAll()
    }

    override fun deleteAll(): Boolean {
        return userDao.deleteAll()
    }

}
