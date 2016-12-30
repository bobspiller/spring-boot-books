package rms.books.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rms.books.application.BooksApplication;
import rms.books.entity.Book;
import rms.books.service.BooksService;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * HTTP Unit tests on {@link rms.books.web.BooksController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = BooksApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class BooksControllerMvcTest {
    private static final String ISBN = "12345";
    private static final String TITLE = "Moby Dick";
    private static final String AUTHOR = "Herman Melville";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private BooksService booksService;

    @MockBean
    private Book book;

    @Test
    public void getBookReturnsOkStatus() {
        given(booksService.getBookByIsbn(ISBN))
                .willReturn(new Book(ISBN, TITLE, AUTHOR));

        ResponseEntity<String> response = restTemplate
                .getForEntity("/books/{isbn}", String.class, ISBN);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void getBookReturnsValueFromService() {
        given(booksService.getBookByIsbn(ISBN))
                .willReturn(new Book(ISBN, TITLE, AUTHOR));

        ResponseEntity<String> response = restTemplate
                .getForEntity("/books/{isbn}", String.class, ISBN);

        assertThat(response.getBody(), hasJsonPath("$.isbn", equalTo(ISBN)));
        assertThat(response.getBody(), hasJsonPath("$.title", equalTo(TITLE)));
        assertThat(response.getBody(), hasJsonPath("$.author", equalTo(AUTHOR)));
    }

    @Test
    public void addNewBookSavesItInTheService() {
        Book book = new Book(ISBN, TITLE, AUTHOR);
        ResponseEntity responseEntity =
                restTemplate.postForEntity("/books", book, Book.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        verify(booksService, times(1)).addNewBook(any(Book.class));
    }
}
