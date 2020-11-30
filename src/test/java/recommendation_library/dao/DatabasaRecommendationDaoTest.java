/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import recommendation_library.UserInterface;
import recommendation_library.domain.BookRecommendation;
import recommendation_library.domain.DatabaseService;
import recommendation_library.io.IO;

/**
 *
 * @author anadis
 */
public class DatabasaRecommendationDaoTest {

    RecommendationDao db_dao;
    DatabaseService service;

    public DatabasaRecommendationDaoTest() {
    }

    @Before
    public void setUp() {
        db_dao = new DatabaseRecommendationDao("src/test/resources/test.db");
        service = new DatabaseService(db_dao);

        List<BookRecommendation> books = db_dao.getAllBookRecommendations();
        for (BookRecommendation b : books) {
            db_dao.deleteBookByTitle(b.getTitle());
        }
    }

    @Test
    public void createRecommendationAddsToTheDatabase() {
        
        service.addBook("Jane", "Hobitti", "Sci-fi thriller", "1234-ABCD", 10);
        assertFalse(service.getAllBookRecommendations().isEmpty());

        BookRecommendation addedRecommendation = service.getAllBookRecommendations().get(0);
        assertEquals("Jane", addedRecommendation.getAuthor());
        assertEquals("Hobitti", addedRecommendation.getTitle());
        assertEquals("Sci-fi thriller", addedRecommendation.getDescription());
        assertEquals("1234-ABCD", addedRecommendation.getIsbn());
        assertEquals(10, addedRecommendation.getPageCount());
        assertEquals(addedRecommendation.getAddDate(), java.time.LocalDate.now().toString());
    }

    @Test
    public void editBookRecommendationEditsAuthor() {
        service.addBook("Bob", "book", "good", "abc", 10);
        service.editBookRecommendation("book", "author", "John");
        BookRecommendation addedRecommendation = service.getAllBookRecommendations().get(0);
        assertEquals("John", addedRecommendation.getAuthor());
    }

    @Test
    public void editBookRecommendationEditsTitle() {
        service.addBook("Bob", "book2", "good", "abc", 10);
        service.editBookRecommendation("book2", "title", "a better title");
        BookRecommendation addedRecommendation = service.getAllBookRecommendations().get(0);
        assertEquals("a better title", addedRecommendation.getTitle());
    }

    @Test
    public void editBookRecommendationEditsISBN() {
        service.addBook("Bob", "book3", "good", "abc", 10);
        service.editBookRecommendation("book3", "isbn", "cba");
        BookRecommendation addedRecommendation = service.getAllBookRecommendations().get(0);
        assertEquals("cba", addedRecommendation.getIsbn());
    }

    @Test
    public void deletesRecommencation() {
        int count = service.getAllBookRecommendations().size();
        System.out.println(count);
        service.addBook("Bob", "book4", "good", "abc", 10);
        assertEquals(count + 1, service.getAllBookRecommendations().size());
        service.deleteBookRecommendation("book4");
        assertEquals(count, service.getAllBookRecommendations().size());
    }

    @Test
    public void existingTitleNotReaddedToDatabase() {
        service.addBook("Bob", "book4", "good", "abc", 10);
        assertTrue(service.bookTitleAlreadyExists("book4"));
        int count = service.getAllBookRecommendations().size();
        boolean added = service.addBook("Henri", "book4", "different book same title", "asd", 11);
        assertFalse(added);
        assertEquals(count, service.getAllBookRecommendations().size());
    }
}
