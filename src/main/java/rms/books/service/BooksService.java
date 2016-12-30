package rms.books.service;

import rms.books.dao.BookDao;
import rms.books.entity.Book;

/**
 * Service which manages {@link Book}s
 */
public class BooksService {
    private final BookDao dao;

    public BooksService(BookDao dao) {
        this.dao = dao;
    }

    public Book getBookByIsbn(String isbn) {
        return dao.findOne(isbn);
    }

    public Iterable<Book> getBooksByAuthor(String author) {
        return dao.findByAuthor(author);
    }

    public Iterable<Book> getBooksByTitle(String title) {
        return dao.findByTitle(title);
    }

    public void addNewBook(Book book) {
        dao.save(book);
    }

    public void deleteBook(Book book) {
        dao.delete(book);
    }
}
