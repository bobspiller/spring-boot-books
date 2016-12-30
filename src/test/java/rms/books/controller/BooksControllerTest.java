package rms.books.controller;

import org.junit.Before;
import org.junit.Test;
import rms.books.entity.Book;
import rms.books.service.BooksService;
import rms.books.web.BooksController;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Solitary unit tests on {@link rms.books.web.BooksController}
 */
public class BooksControllerTest {
    BooksService booksService = mock(BooksService.class);
    private Book book = mock(Book.class);
    private Book anotherBook = mock(Book.class);
    Iterable<Book> books = Arrays.asList(book, anotherBook);
    private BooksController controllerUnderTest;

    @Before
    public void requiresBooksService() {
        controllerUnderTest = new BooksController(booksService);
        assertThat(controllerUnderTest).isNotNull();
    }

    @Test
    public void getSpecificBookDelegatesToService() {
        String isbn = "12345";
        when(booksService.getBookByIsbn(isbn)).thenReturn(book);
        assertThat(controllerUnderTest.getSpecificBook(isbn)).isEqualTo(book);
        verify(booksService).getBookByIsbn(isbn);
    }

    @Test
    public void addBookDelegatesToService() {
        controllerUnderTest.addNewBook(book);
        verify(booksService).addNewBook(book);
    }
}
