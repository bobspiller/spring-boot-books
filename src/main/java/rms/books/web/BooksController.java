package rms.books.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rms.books.entity.Book;
import rms.books.service.BooksService;

/**
 * Main API controller.
 */
@RestController
public class BooksController {
    private final BooksService booksService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/books/{isbn}")
    public Book getSpecificBook(@PathVariable String isbn) {
        Book book = booksService.getBookByIsbn(isbn);
        logger.debug("getSpecificBook: returning book={}", book);
        return book;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/books")
    public void addNewBook(@RequestBody  Book book) {
        logger.debug("addNewBook: saving book={}", book);
        booksService.addNewBook(book);
    }
}
