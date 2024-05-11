package output

import entity.Book

interface IConsole {
    fun showMessage(message: String, lineBreak: Boolean = true)
    fun show(bookList: List<Book>?, message: String = "All books")
}