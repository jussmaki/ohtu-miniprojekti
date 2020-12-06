/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.domain;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import recommendation_library.dao.InMemoryRecommendationDao;
import recommendation_library.dao.RecommendationDao;

/**
 *
 * @author timot
 */
public class DatabaseServiceTest {
    RecommendationDao dao;
    DatabaseService service;
    
    @Before
    public void setUp() {
        dao = new InMemoryRecommendationDao();
        service = new DatabaseService(dao);
    }
    
    @Test
    public void videoTitleSearchingWorksForExistingTitle() {
        service.addVideo("1234", "Test", "Test video", new ArrayList<String>());
        assertTrue(service.videoTitleAlreadyExists("Test"));
    }
    
    @Test
    public void videoTitleSearchingWorksForNonexistingTitle() {
        assertFalse(service.videoTitleAlreadyExists("Test"));
        service.addVideo("1234", "Test", "Test video", new ArrayList<String>());
        assertFalse(service.videoTitleAlreadyExists("Test2"));
    }
    
    @Test
    public void deletingVideoWorks() {
        service.addVideo("1234", "Test", "Test video", new ArrayList<String>());
        assertTrue(service.videoTitleAlreadyExists("Test"));
        service.deleteVideoRecommendation("Test");
        assertFalse(service.videoTitleAlreadyExists("Test"));
    }
    
    @Test
    public void deletingNonexistingVideoWorks() {
        service.addVideo("1234", "Test", "Test video", new ArrayList<String>());
        assertTrue(service.videoTitleAlreadyExists("Test"));
        assertFalse(service.deleteVideoRecommendation("Test1"));
        assertTrue(service.videoTitleAlreadyExists("Test"));
    }
    
    @Test
    public void creatingAlreadyExistingBookFails() {
        service.addBook("Test", "Title", "Book", "USBN", 10, new ArrayList<String>());
        assertFalse(service.addBook("Tet", "Title", "A", "AAA", 0, new ArrayList<String>()));
    }
    
    @Test
    public void creatingAlreadyExistingVideoFails() {
        service.addVideo("1234", "Test", "Test video", new ArrayList<String>());
        assertFalse(service.addVideo("134", "Test", "Teest video", new ArrayList<String>()));
    }
    
    @Test
    public void gettingAllRecommendationsSortedWorks() {
        service.addVideo("1234", "Test", "Test video", new ArrayList<String>());
        service.addBook("Test", "Title", "Book", "USBN", 10, new ArrayList<String>());
        List<Recommendation> r = service.getAllRecommendationsSortedByCreatedDate();
        assertEquals(2, r.size());
        VideoRecommendation v = service.getAllVideoRecommendations().get(0);
        BookRecommendation b = service.getAllBookRecommendations().get(0);
        assertTrue(r.contains(b));
        assertTrue(r.contains(v));
    }
    
    @Test
    public void existingBookTitleNotReaddedToDatabase() {
        service.addBook("Bob", "book4", "good", "abc", 10, new ArrayList<String>());
        assertTrue(service.bookTitleAlreadyExists("book4"));
        int count = service.getAllBookRecommendations().size();
        boolean added = service.addBook("Henri", "book4", "different book same title", "asd", 11, new ArrayList<String>());
        assertFalse(added);
        assertEquals(count, service.getAllBookRecommendations().size());
    }
    
}
