package rms.books.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import rms.books.dao.BookDao;
import rms.books.service.BooksService;
import rms.books.web.BooksController;

/**
 * Spring context configuration.
 */
@Configuration
@EnableJpaRepositories(basePackages = "rms.books.dao")
@EntityScan(basePackages = "rms.books.entity")
public class BooksConfiguration {
    @Autowired
    private BookDao bookDao;

    @Bean
    public BooksService booksService(BookDao bookDao) {
        return new BooksService(bookDao);
    }

    @Bean
    public BooksController booksController(BooksService booksService) {
        return new BooksController(booksService);
    }
}
