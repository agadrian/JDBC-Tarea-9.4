package output

import entity.Book

/**
 * Implementación de la interfaz [IConsole] para mostrar mensajes en la consola.
 */
class Console: IConsole {

    /**
     * Muestra un mensaje en la consola.
     *
     * @param message El mensaje a mostrar.
     * @param lineBreak Indica si se debe agregar un salto de línea al final del mensaje.
     */
    override fun showMessage(message: String, lineBreak: Boolean){
        if (lineBreak) println(message) else print(message)
    }


    /**
     * Muestra una lista de libros en la consola.
     *
     * @param bookList La lista de libros a mostrar.
     * @param message Mensaje opcional que se mostrará antes de la lista de libros.
     */
    override fun show(bookList: List<Book>?, message: String){
        if (bookList != null){
            if (bookList.isNotEmpty()){
                showMessage(message)
                bookList.forEachIndexed { index, book ->
                    showMessage("${index + 1}. $book ")
                }
            }else{
                showMessage("No books found!")
            }
        }
    }
}

