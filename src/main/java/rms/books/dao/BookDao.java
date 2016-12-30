package rms.books.dao;

import org.springframework.data.repository.CrudRepository;
import rms.books.entity.Book;

/**
 * Spring data JPA DOA for {@link Book}s.
 */
public interface BookDao extends CrudRepository<Book, String> {
    Iterable<Book> findByTitle(String title);
    Iterable<Book> findByAuthor(String author);
}
