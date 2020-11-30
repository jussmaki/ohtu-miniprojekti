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
 * @author anadis
 */
public class DatabasaRecommendationDaoTest {

    RecommendationDao db_dao;
    public DatabasaRecommendationDaoTest() {
    }

    @Before
    public void setUp() {
        db_dao = new DatabaseRecommendationDao("src/test/resources/test.db");

        List<BookRecommendation> books = db_dao.getAllBookRecommendations();
        for (BookRecommendation b : books) {
            db_dao.deleteBookByTitle(b.getTitle());
        }
        List<VideoRecommendation> videos = db_dao.getAllVideoRecommendations();
        for (VideoRecommendation v : videos) {
            db_dao.deleteVideoByTitle(v.getTitle());
        }
        
    }

    @Test
    public void createBookRecommendationAddsToTheDatabase() {
        
        db_dao.createBookRecommendation("Jane", "Hobitti", "Sci-fi thriller", "1234-ABCD", 10);
        assertFalse(db_dao.getAllBookRecommendations().isEmpty());

        BookRecommendation addedRecommendation = db_dao.getAllBookRecommendations().get(0);
        assertEquals("Jane", addedRecommendation.getAuthor());
        assertEquals("Hobitti", addedRecommendation.getTitle());
        assertEquals("Sci-fi thriller", addedRecommendation.getDescription());
        assertEquals("1234-ABCD", addedRecommendation.getIsbn());
        assertEquals(10, addedRecommendation.getPageCount());
        assertEquals(addedRecommendation.getAddDate(), java.time.LocalDate.now().toString());
    }

    @Test
    public void editBookRecommendationEditsAuthor() {
        db_dao.createBookRecommendation("Bob", "book", "good", "abc", 10);
        db_dao.editBookRecommendation("book", "author", "John");
        BookRecommendation addedRecommendation = db_dao.getAllBookRecommendations().get(0);
        assertEquals("John", addedRecommendation.getAuthor());
    }

    @Test
    public void editBookRecommendationEditsTitle() {
        db_dao.createBookRecommendation("Bob", "book2", "good", "abc", 10);
        db_dao.editBookRecommendation("book2", "title", "a better title");
        BookRecommendation addedRecommendation = db_dao.getAllBookRecommendations().get(0);
        assertEquals("a better title", addedRecommendation.getTitle());
    }

    @Test
    public void editBookRecommendationEditsISBN() {
        db_dao.createBookRecommendation("Bob", "book3", "good", "abc", 10);
        db_dao.editBookRecommendation("book3", "isbn", "cba");
        BookRecommendation addedRecommendation = db_dao.getAllBookRecommendations().get(0);
        assertEquals("cba", addedRecommendation.getIsbn());
    }

    @Test
    public void deleteBookByTitle() {
        int count = db_dao.getAllBookRecommendations().size();
        System.out.println(count);
        db_dao.createBookRecommendation("Bob", "book4", "good", "abc", 10);
        assertEquals(count + 1, db_dao.getAllBookRecommendations().size());
        db_dao.deleteBookByTitle("book4");
        assertEquals(count, db_dao.getAllBookRecommendations().size());
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
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        assertFalse(db_dao.getAllVideoRecommendations().isEmpty());

        VideoRecommendation addedRecommendation = db_dao.getAllVideoRecommendations().get(0);
        assertEquals("https://www.youtube.com/watch?v=dQw4w9WgXcQ", addedRecommendation.getUrl());
        assertEquals("How to get full marks on all courses 101", addedRecommendation.getTitle());
        assertEquals("Very secret", addedRecommendation.getDescription());
        assertEquals(addedRecommendation.getAddDate(), java.time.LocalDate.now().toString());
    }
    
    @Test
    public void editVideoRecommendationEditsUrl() {
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        db_dao.editVideoRecommendation("How to get full marks on all courses 101", "url", "google.fi");
        VideoRecommendation addedRecommendation = db_dao.getAllVideoRecommendations().get(0);
        assertEquals("google.fi", addedRecommendation.getUrl());
    }

    @Test
    public void editVideoRecommendationEditsTitle() {
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        db_dao.editVideoRecommendation("How to get full marks on all courses 101", "Title", "An obvious Rick Roll");
        VideoRecommendation addedRecommendation = db_dao.getAllVideoRecommendations().get(0);
        assertEquals("An obvious Rick Roll", addedRecommendation.getTitle());
    }

    @Test
    public void editVideoRecommendationEditsDescription() {
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        db_dao.editVideoRecommendation("How to get full marks on all courses 101", "Description", "An obvious Rick Roll");
        VideoRecommendation addedRecommendation = db_dao.getAllVideoRecommendations().get(0);
        assertEquals("An obvious Rick Roll", addedRecommendation.getDescription());
    }

    @Test
    public void deleteVideoRecommendation() {
        assertEquals(0, db_dao.getAllVideoRecommendations().size());
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        assertEquals(1, db_dao.getAllVideoRecommendations().size());
        db_dao.deleteVideoByTitle("How to get full marks on all courses 101");
        assertEquals(0, db_dao.getAllVideoRecommendations().size());
    }

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
