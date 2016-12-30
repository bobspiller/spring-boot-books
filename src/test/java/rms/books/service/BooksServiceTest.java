package rms.books.service;

import org.junit.Before;
import org.junit.Test;
import rms.books.dao.BookDao;
import rms.books.entity.Book;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Isolated unit tests on {@link BooksService}.
 */
public class BooksServiceTest {
    private BookDao dao = mock(BookDao.class);
    private Book book = mock(Book.class);
    private Book anotherBook = mock(Book.class);
    private Iterable<Book> books = Arrays.asList(book, anotherBook);
    private BooksService serviceUnderTest;

    @Before
    public void bookServiceRequiresDao() {
        serviceUnderTest = new BooksService(dao);
        assertThat(serviceUnderTest).isNotNull();
    }

    @Test
    public void getByIsbnDelegatesToDao() {
        String isbn = "1234567";
        when(dao.findOne(isbn)).thenReturn(book);
        assertThat(serviceUnderTest.getBookByIsbn(isbn)).isEqualTo(book);
        verify(dao).findOne(any(String.class));
    }

    @Test
    public void getBooksByAuthorDelegatesToDao() {
        String author = "Douglas Adams";
        when(dao.findByAuthor(author)).thenReturn(books);
        assertThat(serviceUnderTest.getBooksByAuthor(author)).isEqualTo(books);
        verify(dao).findByAuthor(any(String.class));
    }

    @Test
    public void getBooksByTitleDelegatesToDao() {
        String title = "Dirk Gently's Holistic Detective Agency";
        when(dao.findByTitle(title)).thenReturn(books);
        assertThat(serviceUnderTest.getBooksByTitle(title)).isEqualTo(books);
        verify(dao).findByTitle(any(String.class));
    }

    @Test
    public void addBookDelegatesToDao() {
        serviceUnderTest.addNewBook(book);
        verify(dao).save(book);
    }

    @Test
    public void deleteBookDelegatesToDao() {
        serviceUnderTest.deleteBook(book);
        verify(dao).delete(book);
    }
}
