package rms.books.dao;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import rms.books.application.BooksConfiguration;
import rms.books.entity.Book;

import static com.google.common.collect.Iterables.size;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests on {@link BookDao}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BooksConfiguration.class)
@DataJpaTest
public class BookDaoTest {
    private static final String ISBN = "55555";
    private static final String ISBN2 = "55556";
    private static final String ISBN3 = "55557";
    private static final String TITLE = "Slaughter House Five";
    private static final String TITLE2 = "Cat's Cradle";
    private static final String TITLE3 = "Nine Princes of Amber";
    private static final String AUTHOR = "Kurt Vonogut";
    private static final String AUTHOR2 = "Roger Zelzny";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookDao bookDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void savePersistsABook() {
        Book bookIn = new Book(ISBN, TITLE, AUTHOR);
        bookDao.save(bookIn);
        Book bookOut = bookDao.findOne(ISBN);
        assertThat(bookOut.getIsbn()).isEqualTo(ISBN);
        assertThat(bookOut.getTitle()).isEqualTo(TITLE);
        assertThat(bookOut.getAuthor()).isEqualTo(AUTHOR);
    }

    @Test
    @Ignore("Until we figure out unique constraints")
    public void savingAnExistingIsbnShouldThrow() {
        Book bookIn = new Book(ISBN, TITLE, AUTHOR);
        bookDao.save(bookIn);
        thrown.expect(DataAccessException.class);
        bookDao.save(bookIn);
        // Should never get here
        assertThat(true).isTrue();
    }

    @Test
    public void findOneReturnsBook() {
        entityManager.persist(new Book(ISBN, TITLE, AUTHOR));
        Book book = bookDao.findOne(ISBN);
        assertThat(book.getIsbn()).isEqualTo(ISBN);
        assertThat(book.getTitle()).isEqualTo(TITLE);
        assertThat(book.getAuthor()).isEqualTo(AUTHOR);
    }

    @Test
    public void findByAuthorReturnsAppropriateBooks() {
        entityManager.persist(new Book(ISBN, TITLE, AUTHOR));
        entityManager.persist(new Book(ISBN2, TITLE2, AUTHOR));
        entityManager.persist(new Book(ISBN3, TITLE3, AUTHOR2));
        assertThat(size(bookDao.findByAuthor(AUTHOR))).isEqualTo(2);
    }

    @Test
    public void findByTitleReturnsAppropriateBooks() {
        entityManager.persist(new Book(ISBN, TITLE, AUTHOR));
        entityManager.persist(new Book(ISBN2, TITLE2, AUTHOR));
        entityManager.persist(new Book(ISBN3, TITLE3, AUTHOR2));
        assertThat(size(bookDao.findByTitle(TITLE2))).isEqualTo(1);
    }

    @Test
    public void findAllReturnsAllTheBooks() {
        entityManager.persist(new Book(ISBN, TITLE, AUTHOR));
        entityManager.persist(new Book(ISBN2, TITLE2, AUTHOR));
        entityManager.persist(new Book(ISBN3, TITLE3, AUTHOR2));
        assertThat(size(bookDao.findAll())).isEqualTo(3);
    }

    @Test
    public void removeDeletesTheAppropriateBook() {
        entityManager.persist(new Book(ISBN, TITLE, AUTHOR));
        entityManager.persist(new Book(ISBN2, TITLE2, AUTHOR));
        entityManager.persist(new Book(ISBN3, TITLE3, AUTHOR2));
        bookDao.delete(ISBN3);
        assertThat(size(bookDao.findAll())).isEqualTo(2);
    }
}
