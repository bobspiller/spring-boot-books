package rms.books.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rms.books.entity.Book;

/**
 * Spring data JPA DOA for {@link Book}s.
 */
@Repository
public interface BookDao extends CrudRepository<Book, String> {
    Iterable<Book> findByTitle(String title);
    Iterable<Book> findByAuthor(String author);
}
