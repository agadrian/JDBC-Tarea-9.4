package dao

import entity.Book
import output.IConsole
import java.sql.SQLException
import java.sql.SQLTimeoutException
import java.util.*
import javax.sql.DataSource


/**
 * Implementación de [IBookDAO] para acceder y manipular los datos de libros en una base de datos H2.
 *
 * @property dataSource Fuente de datos para obtener conexiones a la base de datos.
 * @property console Objeto para mostrar mensajes de error.
 */
class BookDAOH2(
    private val dataSource: DataSource,
    private val console: IConsole
): IBookDAO {


    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param book El libro a crear.
     * @return El libro creado, o null si no se pudo crear.
     */
    override fun create(book: Book): Book? {
        val sql = "INSERT INTO book (id, title, autor, yearPublication) VALUES (?, ?, ?, ?)"

        return try{
            dataSource.connection.use {connection ->
                connection.prepareStatement(sql).use {stmt ->
                    stmt.setString(1, book.id.toString())
                    stmt.setString(2, book.title)
                    stmt.setString(3, book.autor)
                    stmt.setInt(4, book.yearPublication)

                    val rs = stmt.executeUpdate()

                    if (rs == 1) {
                        book
                    }else{
                        console.showMessage("ERROR - Insert query failed ($rs rows affected) ")
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("ERROR - Insert query failed ${e.message}")
            null
        }catch (e: SQLTimeoutException){
            console.showMessage("ERROR - Database access error occurred: ${e.message}")
            null
        }
    }


    /**
     * Obtiene todos los libros de la base de datos.
     *
     * @return Una lista de todos los libros en la base de datos, o null si no hay libros.
     */
    override fun getAll(): List<Book>? {
        val sql = "SELECT * FROM book"
        return try{
            dataSource.connection.use{connection ->
                connection.prepareStatement(sql).use{stmt ->
                    val rs = stmt.executeQuery()
                    val books = mutableListOf<Book>()

                    while (rs.next()){
                        books.add(
                            Book(
                                id = UUID.fromString(rs.getString("id")),
                                title = rs.getString("title"),
                                autor = rs.getString("autor"),
                                yearPublication = rs.getInt("yearPublication")
                            )
                        )
                    }
                    books
                }
            }
        }catch (e: SQLException){
            console.showMessage("ERROR - Get all rows failed ${e.message}")
            null
        }catch (e: SQLTimeoutException){
            console.showMessage("ERROR - Database access error occurred: ${e.message}")
            null
        }
    }


    /**
     * Obtiene un libro de la base de datos por su ID.
     *
     * @param id El ID del libro a obtener.
     * @return El libro con el ID especificado, o null si no se encuentra.
     */
    override fun getById(id: UUID): Book? {
        val sql = "SELECT * FROM book WHERE id = ?"
        return try {
            dataSource.connection.use { connection ->
                connection.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())

                    val rs = stmt.executeQuery()

                    if (rs.next()){
                        Book(
                            id = UUID.fromString(rs.getString("id")),
                            title = rs.getString("title"),
                            autor = rs.getString("autor"),
                            yearPublication = rs.getInt("yearPublication")
                        )
                    }else{
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("ERROR - Get row by id failed ${e.message}")
            null
        }catch (e: SQLTimeoutException){
            console.showMessage("ERROR - Database access error occurred: ${e.message}")
            null
        }

    }


    /**
     * Actualiza un libro existente en la base de datos.
     *
     * @param book El libro con los nuevos datos.
     * @return El libro actualizado, o null si no se pudo actualizar.
     */
    override fun update(book: Book): Book? {
        val sql = "UPDATE book SET title = ?, autor = ?, yearPublication = ? WHERE id = ?"

        return try {

            dataSource.connection.use { connection ->
                connection.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.title)
                    stmt.setString(2, book.autor)
                    stmt.setInt(3, book.yearPublication)

                    book
                }
            }

        }catch (e: SQLException){
            console.showMessage("ERROR - Update rows failed ${e.message}")
            null
        }catch (e: SQLTimeoutException){
            console.showMessage("ERROR - Database access error occurred: ${e.message}")
            null
        }
    }


    /**
     * Elimina un libro de la base de datos por su ID.
     *
     * @param id El ID del libro a eliminar.
     * @return true si el libro se eliminó correctamente, false en caso contrario.
     */
    override fun delete(id: UUID): Boolean {
        val sql = "DELETE FROM book WHERE id = ?"

        return try {
            dataSource.connection.use { connection ->
                connection.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())

                    // Devuelve true/false si se ha eliminado correctamente o false si no
                    val rs = stmt.executeUpdate()

                    rs == 1
                }
            }
        }catch (e: SQLException){
            console.showMessage("ERROR - Delete rows failed ${e.message}")
            false
        }catch (e: SQLTimeoutException){
            console.showMessage("ERROR - Database access error occurred: ${e.message}")
            false
        }
    }


    /**
     * Elimina todos los libros de la base de datos.
     *
     * @return true si se eliminaron todos los libros correctamente, false en caso contrario.
     */
    override fun deleteAll(): Boolean {
        val sql = "DELETE FROM book"

        return try {

            dataSource.connection.use { connection ->
                connection.prepareStatement(sql).use { stmt ->
                    stmt.executeUpdate() > 0
                }
            }

        }catch (e: SQLException){
            console.showMessage("ERROR - Delete all rows failed ${e.message}")
            false
        }catch (e: SQLTimeoutException){
            console.showMessage("ERROR - Database access error occurred: ${e.message}")
            false
        }
    }
}