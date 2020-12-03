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
import recommendation_library.domain.BookRecommendation;
import recommendation_library.domain.VideoRecommendation;

/**
 *
 * @author hajajaim
 */
public class InMemoryRecommendationDaoTest {

    RecommendationDao dao;
    
    @Before
    public void setUp() {
        dao = new InMemoryRecommendationDao();
    }

    @Test
    public void createBookRecommendationAddsToTheDatabase() {

        dao.createBookRecommendation("Jane", "Hobitti", "Sci-fi thriller", "1234-ABCD", 10);
        assertFalse(dao.getAllBookRecommendations().isEmpty());

        BookRecommendation addedRecommendation = dao.getAllBookRecommendations().get(0);
        assertEquals("Jane", addedRecommendation.getAuthor());
        assertEquals("Hobitti", addedRecommendation.getTitle());
        assertEquals("Sci-fi thriller", addedRecommendation.getDescription());
        assertEquals("1234-ABCD", addedRecommendation.getIsbn());
        assertEquals(10, addedRecommendation.getPageCount());
        assertEquals(addedRecommendation.getAddDate(), java.time.LocalDate.now().toString());
    }

    @Test
    public void editBookRecommendationEditsAuthor() {
        dao.createBookRecommendation("Bob", "book", "good", "abc", 10);
        dao.editBookRecommendation("book", "author", "John");
        BookRecommendation addedRecommendation = dao.getAllBookRecommendations().get(0);
        assertEquals("John", addedRecommendation.getAuthor());
    }

    @Test
    public void editBookRecommendationEditsTitle() {
        dao.createBookRecommendation("Bob", "book2", "good", "abc", 10);
        dao.editBookRecommendation("book2", "title", "a better title");
        BookRecommendation addedRecommendation = dao.getAllBookRecommendations().get(0);
        assertEquals("a better title", addedRecommendation.getTitle());
    }

    @Test
    public void editBookRecommendationEditsISBN() {
        dao.createBookRecommendation("Bob", "book3", "good", "abc", 10);
        dao.editBookRecommendation("book3", "isbn", "cba");
        BookRecommendation addedRecommendation = dao.getAllBookRecommendations().get(0);
        assertEquals("cba", addedRecommendation.getIsbn());
    }

    @Test
    public void editBookRecommendationEditsDescription() {
        dao.createBookRecommendation("Bob", "book3", "good", "abc", 10);
        dao.editBookRecommendation("book3", "description", "bad");
        BookRecommendation addedRecommendation = dao.getAllBookRecommendations().get(0);
        assertEquals("bad", addedRecommendation.getDescription());
    }

    @Test
    public void editBookRecommendationEditsPageCount() {
        dao.createBookRecommendation("Bob", "book3", "good", "abc", 10);
        dao.editBookRecommendation("book3", "pagecount", "100");
        BookRecommendation addedRecommendation = dao.getAllBookRecommendations().get(0);
        assertEquals(100, addedRecommendation.getPageCount());
    }
    
    @Test
    public void deleteBookByTitle() {
        // created second entry to ensure for loop works correctly in delete method
        dao.createBookRecommendation("Rob", "book451", "-", "abc", 10);
        dao.createBookRecommendation("Bob", "book4", "good", "abc", 10);
        assertEquals(2, dao.getAllBookRecommendations().size());
        dao.deleteBookByTitle("book4");
        assertEquals(1, dao.getAllBookRecommendations().size());
    }

//    Should be moved to DatabaseServiceTest class
//    @Test
//    public void existingBookTitleNotReaddedToDatabase() {
//        db_dao.createBookRecommendation("Bob", "book4", "good", "abc", 10);
//        assertTrue(db_dao.bookTitleAlreadyExists("book4"));
//        int count = db_dao.getAllBookRecommendations().size();
//        boolean added = db_dao.createBookRecommendation("Henri", "book4", "different book same title", "asd", 11);
//        assertFalse(added);
//        assertEquals(count, db_dao.getAllBookRecommendations().size());
//    }

    @Test
    public void createVideoRecommendationAddsToTheDatabase() {
        dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        assertFalse(dao.getAllVideoRecommendations().isEmpty());

        VideoRecommendation addedRecommendation = dao.getAllVideoRecommendations().get(0);
        assertEquals("https://www.youtube.com/watch?v=dQw4w9WgXcQ", addedRecommendation.getUrl());
        assertEquals("How to get full marks on all courses 101", addedRecommendation.getTitle());
        assertEquals("Very secret", addedRecommendation.getDescription());
        assertEquals(addedRecommendation.getAddDate(), java.time.LocalDate.now().toString());
    }

    @Test
    public void editVideoRecommendationEditsUrl() {
        dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        dao.editVideoRecommendation("How to get full marks on all courses 101", "url", "google.fi");
        VideoRecommendation addedRecommendation = dao.getAllVideoRecommendations().get(0);
        dao.getAllVideoRecommendations().forEach(r -> System.err.println(r.getUrl()));
        assertEquals("google.fi", addedRecommendation.getUrl());
    }

    @Test
    public void editVideoRecommendationEditsTitle() {
        dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        dao.editVideoRecommendation("How to get full marks on all courses 101", "Title", "An obvious Rick Roll");
        VideoRecommendation addedRecommendation = dao.getAllVideoRecommendations().get(0);
        assertEquals("An obvious Rick Roll", addedRecommendation.getTitle());
    }

    @Test
    public void editVideoRecommendationEditsDescription() {
        dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        dao.editVideoRecommendation("How to get full marks on all courses 101", "Description", "An obvious Rick Roll");
        VideoRecommendation addedRecommendation = dao.getAllVideoRecommendations().get(0);
        assertEquals("An obvious Rick Roll", addedRecommendation.getDescription());
    }

    @Test
    public void deleteVideoRecommendation() {
        assertEquals(0, dao.getAllVideoRecommendations().size());
        dao.createVideoRecommendation("https://www.youtube.com/watch?v=BjDebmqFRuc", "Singing class", "Excellent singing");
        dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        assertEquals(2, dao.getAllVideoRecommendations().size());
        dao.deleteVideoByTitle("How to get full marks on all courses 101");
        assertEquals(1, dao.getAllVideoRecommendations().size());
    }

//    belongs to DatabaseService testing 
//    @Test
//    public void existingVideoTitleNotReaddedToDatabase() {
//        db_dao.addVideo("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
//        assertTrue(db_dao.videoTitleAlreadyExists("How to get full marks on all courses 101"));
//        int count = db_dao.getAllVideoRecommendations().size();
//        boolean added = db_dao.addVideo("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
//        assertFalse(added);
//        assertEquals(count, db_dao.getAllVideoRecommendations().size());
//    }
}
