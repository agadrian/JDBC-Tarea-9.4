import dao.BookDAOH2
import db_connection.DataSourceFactory
import entity.Book
import output.Console
import service.BookServiceImpl


fun main() {
    // Crear consola
    val console = Console()

    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.JDBC)

    // Creamos la instancia de BookDAO
    val bookDao = BookDAOH2(dataSource, console)

    // Creamos la instancia de bookService
    val bookService = BookServiceImpl(bookDao)



    // Creamos un nuevo libro
    val newBook = Book(title = "Harry el gafota", autor = "Diego Cano", yearPublication = 2023)
    var createdBook = bookService.create(newBook)
    console.showMessage("Created book: ${createdBook ?: "error"}")


    val newBook2 = bookService.create(Book(title = "1984", autor = "George Orwell", yearPublication = 1949))
    val newBook3 = bookService.create(Book(title = "Orgullo y prejuicio", autor = "Jane Austen", yearPublication = 1813))
    console.showMessage("Created book: ${newBook2 ?: "error"}")
    console.showMessage("Created book: ${newBook3 ?: "error"}")


    // Obtenemos un libro por su ID
    val foundBook =
        if (createdBook != null) bookService.getById(createdBook.id) else null
    console.showMessage("Found book: ${foundBook ?: "error"}")

    // Actualizamos el libro
    val updatedBook = foundBook!!.copy(title = "Piojos en la nariz")
    val savedBook = bookService.update(updatedBook)
    console.showMessage("Updated book: ${savedBook ?: "error"}")

    val otherBook = Book(title = "Juan manostijera", autor = "Perico palote", yearPublication = 2002)
    createdBook = bookService.create(otherBook)
    console.showMessage("Created book: ${createdBook ?: "error"}")


    // Obtenemos todos los libros
    var allBooks = bookService.getAll()
    console.show(allBooks)

    // Eliminamos el libro
    if (bookService.delete(otherBook.id)){
        console.showMessage("Book deleted")
    }else{
        console.showMessage("Book deleted")
    }


    // Obtenemos todos los libros
    allBooks = bookService.getAll()
    console.showMessage("All books: $allBooks")

    // Eliminamos el libro
    bookService.delete(otherBook.id)
    console.showMessage("Book deleted: $otherBook")



/*
    // Eliminar todas las rows de la tabla
    val deleteAll = bookService.deleteAll()
    if (deleteAll) {
        console.showMessage("All rows deleted")
    }
*/

}
