/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import recommendation_library.domain.BlogRecommendation;
import recommendation_library.domain.BookRecommendation;
import recommendation_library.domain.PodcastRecommendation;
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
        assertEquals(0, dao.getBookIdByTitle("titteli"));
    }

    @Test
    public void editBookRecommendationEditsAuthor() {
        dao.createBookRecommendation("Bob", "book", "good", "abc", 10);
        dao.editBookRecommendation("book", "author", "John");
        BookRecommendation addedRecommendation = dao.getAllBookRecommendations().get(0);
        dao.editBookRecommendation("titteli", "author", "Jane");
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
        dao.deleteBookByTitle("NONEXISTING TITLE");
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
        assertEquals(0, dao.getVideoIdByTitle("Wrong title"));
    }

    @Test
    public void editVideoRecommendationEditsUrl() {
        dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        dao.editVideoRecommendation("How to get full marks on all courses 101", "url", "google.fi");
        VideoRecommendation addedRecommendation = dao.getAllVideoRecommendations().get(0);
        dao.getAllVideoRecommendations().forEach(r -> System.err.println(r.getUrl()));
        dao.editVideoRecommendation("wrong title", "url", "notgoogle.fi");
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
        dao.deleteVideoByTitle("wrong title");
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
    @Test
    public void createBlogRecommendationAddsToTheDatabase() {
        dao.createBlogRecommendation("www.blog.com", "my blog", "bob", "description");
        assertFalse(dao.getAllBlogRecommendations().isEmpty());

        BlogRecommendation addedRecommendation = dao.getAllBlogRecommendations().get(0);
        assertEquals("bob", addedRecommendation.getAuthor());
        assertEquals("my blog", addedRecommendation.getTitle());
        assertEquals("description", addedRecommendation.getDescription());
        assertEquals("www.blog.com", addedRecommendation.getUrl());
        assertEquals(addedRecommendation.getAddDate(), java.time.LocalDate.now().toString());
    }

    @Test
    public void editingBlogEditsTheRightBlogAndRightFields() {
        dao.createBlogRecommendation("url", "title", "author", "description");
        dao.editBlogRecommendation("title", "title", "newTitle");
        assertEquals(0, dao.getBlogIdByTitle("title"));
        assertEquals("newTitle", dao.getAllBlogRecommendations().get(0).getTitle());
        dao.editBlogRecommendation("newTitle", "description", "newValue");
        assertEquals(1, dao.getBlogIdByTitle("newTitle"));
        assertEquals("newValue", dao.getAllBlogRecommendations().get(0).getDescription());
    }

    @Test
    public void deletingBlogDeletes() {
        dao.createBlogRecommendation("url", "title", "author", "description");
        dao.deleteBlogByTitle("wrong title");
        assertFalse(dao.getAllBlogRecommendations().isEmpty());
        assertEquals(1, dao.getBlogIdByTitle("title"));
        dao.deleteBlogByTitle("title");
        assertTrue(dao.getAllBlogRecommendations().isEmpty());
    }

    @Test
    public void createPodcastRecommendationAddsToTheDatabase() {
        dao.createPodcastRecommendation("author", "title", "description", "podcastName");
        assertFalse(dao.getAllPodcastRecommendations().isEmpty());

        PodcastRecommendation addedRecommendation = dao.getAllPodcastRecommendations().get(0);
        assertEquals("author", addedRecommendation.getAuthor());
        assertEquals("title", addedRecommendation.getTitle());
        assertEquals("description", addedRecommendation.getDescription());
        assertEquals("podcastName", addedRecommendation.getPodcastName());
        assertEquals(addedRecommendation.getAddDate(), java.time.LocalDate.now().toString());
        assertEquals(0, dao.getPodcastIdByTitle("wrong title"));
    }

    @Test
    public void editingPodcastEditsCorrectPodcastAndCorrectField() {
        dao.createPodcastRecommendation("author", "title", "description", "name");
        dao.editPodcastRecommendation("title", "title", "newTitle");
        dao.editBlogRecommendation("title", "author", "newAuthor");
        assertEquals("newTitle", dao.getAllPodcastRecommendations().get(0).getTitle());
        assertEquals(0, dao.getPodcastIdByTitle("title"));
        assertEquals("author", dao.getAllPodcastRecommendations().get(0).getAuthor());
    }

    @Test
    public void deletingPodcastsDeletes() {
        dao.createPodcastRecommendation("author", "title", "description", "name");
        dao.deletePodcastByTitle("wrong");
        assertFalse(dao.getAllPodcastRecommendations().isEmpty());
        assertEquals(1, dao.getPodcastIdByTitle("title"));
        dao.deletePodcastByTitle("title");
        assertTrue(dao.getAllPodcastRecommendations().isEmpty());
    }

    @Test
    public void addingTimeStampsToVideo() {
        dao.createVideoRecommendation("url", "title", "description");
        dao.createVideoRecommendation("url", "title2", "description");
        assertEquals(2, dao.getAllVideoRecommendations().size());
        assertEquals(0, dao.getVideoIdByTitle("titteli"));
        dao.addTimeStampToVideo(dao.getVideoIdByTitle("title"), "timestamp", "comment");
        assertEquals("timestamp", dao.getAllTimestampsForVideo(1).get(0).getTimeStamp());
        assertTrue(dao.getAllTimestampsForVideo(2).isEmpty());
    }

    @Test
    public void editingTimestampInVideo() {
        dao.createVideoRecommendation("url", "title", "description");
        dao.addTimeStampToVideo(1, "timestamp", "comment");
        dao.editTimestampForVideo(1, dao.getTimestampIdByTitle(1, "timestamp"), "timestamp", "1*4*5");
        assertEquals(0, dao.getTimestampIdByTitle(1, "timestamp"));
        assertEquals(1, dao.getTimestampIdByTitle(1, "1*4*5"));
        dao.editTimestampForVideo(1, 2, "comment", "newValue");
        assertEquals("comment", dao.getAllTimestampsForVideo(1).get(0).getComment());
        dao.editTimestampForVideo(1, 1, "comment", "newComment");
        assertEquals("newComment", dao.getAllTimestampsForVideo(1).get(0).getComment());
    }
    
    @Test
    public void deletingTImestampDeletesCorrectly() {
        dao.createVideoRecommendation("url", "title", "description");
        dao.addTimeStampToVideo(1, "timestamp", "comment");
        dao.deleteTimestamp(1, 2);
        dao.deleteTimestamp(2, 1);
        dao.deleteTimestamp(0, 0);
        assertFalse(dao.getAllTimestampsForVideo(1).isEmpty());
        dao.deleteTimestamp(1, 1);
        assertTrue(dao.getAllTimestampsForVideo(1).isEmpty());
    }
    

}
