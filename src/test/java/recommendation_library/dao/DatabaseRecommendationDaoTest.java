/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import recommendation_library.domain.BlogRecommendation;
import recommendation_library.domain.BookRecommendation;
import recommendation_library.domain.PodcastRecommendation;
import recommendation_library.domain.VideoRecommendation;

/**
 *
 * @author anadis
 */
public class DatabaseRecommendationDaoTest {

    RecommendationDao db_dao;

    @Before
    public void setUp() {
        db_dao = new DatabaseRecommendationDao("src/test/resources/test.db");

    }

    @After
    public void tearDown() {

        List<BookRecommendation> books = db_dao.getAllBookRecommendations();
        for (BookRecommendation b : books) {
            db_dao.deleteBookByTitle(b.getTitle());
        }
        List<VideoRecommendation> videos = db_dao.getAllVideoRecommendations();
        for (VideoRecommendation v : videos) {
            db_dao.deleteVideoByTitle(v.getTitle());
        }
        List<BlogRecommendation> blogs = db_dao.getAllBlogRecommendations();
        for (BlogRecommendation b : blogs) {
            db_dao.deleteBlogByTitle(b.getTitle());
        }
        List<PodcastRecommendation> pods = db_dao.getAllPodcastRecommendations();
        for (PodcastRecommendation p : pods) {
            db_dao.deletePodcastByTitle(p.getTitle());
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
        assertEquals(0, db_dao.getVideoIdByTitle("wrong"));
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

//    This test does not belong to this class since databse does not check for existing title
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
    public void gettingAllTimestampsForVideoReturnsOnlyTimeStampsForTheSpecifiedVideo() {
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        db_dao.addTimeStampToVideo(1, "0:00", "start");
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "Rick Astley - Never Gonna Give You Up", "Rick Roll");
        db_dao.addTimeStampToVideo(2, "0:00", "start");
        assertEquals(1, db_dao.getAllTimestampsForVideo(1).size());
    }

    @Test
    public void editingTimeStampFieldEditsOnlySpecifiedField() {
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        db_dao.addTimeStampToVideo(1, "0:00", "start");
        db_dao.editTimestampForVideo(1, 1, "timestamp", "0:01");
        assertTrue(db_dao.getAllTimestampsForVideo(1).get(0).getTimeStamp().equals("0:01"));
        assertTrue(db_dao.getAllTimestampsForVideo(1).get(0).getComment().equals("start"));
        db_dao.editTimestampForVideo(1, 1, "comment", "The beginning");
        assertTrue(db_dao.getAllTimestampsForVideo(1).get(0).getTimeStamp().equals("0:01"));
        assertTrue(db_dao.getAllTimestampsForVideo(1).get(0).getComment().equals("The beginning"));
    }

    @Test
    public void deletingTimestampDeletesOnlyOne() {
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "How to get full marks on all courses 101", "Very secret");
        db_dao.addTimeStampToVideo(1, "0:00", "start");
        db_dao.createVideoRecommendation("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "Rick Astley - Never Gonna Give You Up", "Rick Roll");
        db_dao.addTimeStampToVideo(2, "0:00", "start");
        db_dao.deleteTimestamp(1, 1);
        assertEquals(0, db_dao.getTimestampIdByTitle(1, "0:00"));
        assertEquals(2, db_dao.getTimestampIdByTitle(2, "0:00"));
    }

    @Test
    public void addingBlogAddsNewBlogToDatabase() {
        db_dao.createBlogRecommendation("url", "title", "author", "description");
        assertFalse(db_dao.getAllBlogRecommendations().isEmpty());
        assertEquals("title", db_dao.getAllBlogRecommendations().get(0).getTitle());
    }

    @Test
    public void addingPodcastAddsNewPodcastAndReturnsItAsPartOfTheList() {
        db_dao.createPodcastRecommendation("author", "title", "description", "name");
        assertFalse(db_dao.getAllPodcastRecommendations().isEmpty());
        assertEquals(1, db_dao.getPodcastIdByTitle("title"));
        assertEquals(0, db_dao.getPodcastIdByTitle("Title"));
    }

    @Test
    public void createBlogRecommendationAddsToTheDatabase() {
        db_dao.createBlogRecommendation("google.fi", "Title", "Jane", "description");
        assertFalse(db_dao.getAllBlogRecommendations().isEmpty());

        BlogRecommendation addedRecommendation = db_dao.getAllBlogRecommendations().get(0);
        assertEquals("Jane", addedRecommendation.getAuthor());
        assertEquals("google.fi", addedRecommendation.getUrl());
        assertEquals("Title", addedRecommendation.getTitle());
        assertEquals("description", addedRecommendation.getDescription());
        assertEquals(addedRecommendation.getAddDate(), java.time.LocalDate.now().toString());
    }

    @Test
    public void editBlogRecommendationEditsAuthor() {
        db_dao.createBlogRecommendation("google.fi", "Title", "Jane", "description");
        db_dao.editBlogRecommendation("Title", "author", "John");
        BlogRecommendation addedRecommendation = db_dao.getAllBlogRecommendations().get(0);
        assertEquals("John", addedRecommendation.getAuthor());
    }

    @Test
    public void editBlogRecommendationEditsTitle() {
        db_dao.createBlogRecommendation("google.fi", "Title", "Jane", "description");
        db_dao.editBlogRecommendation("Title", "title", "BLORG");
        BlogRecommendation addedRecommendation = db_dao.getAllBlogRecommendations().get(0);
        assertEquals("BLORG", addedRecommendation.getTitle());
    }

    @Test
    public void editBlogRecommendationEditsUrl() {
        db_dao.createBlogRecommendation("google.fi", "Title", "Jane", "description");
        db_dao.editBlogRecommendation("Title", "url", "cba");
        assertEquals(0, db_dao.getBlogIdByTitle("wrongtitle"));
        db_dao.editBlogRecommendation("Title", "title", "right");
        db_dao.editBlogRecommendation("Title", "url", "newValue.com");
        db_dao.editBlogRecommendation("right", "author", "John");
        BlogRecommendation addedRecommendation = db_dao.getAllBlogRecommendations().get(0);
        assertEquals("cba", addedRecommendation.getUrl());
        assertEquals("right", addedRecommendation.getTitle());
        assertEquals("John", addedRecommendation.getAuthor());
    }

    @Test
    public void editBlogRecommendationEditsDescription() {
        db_dao.createBlogRecommendation("google.fi", "Title", "Jane", "description");
        db_dao.editBlogRecommendation("Title", "description", "cba");
        assertEquals(0, db_dao.getBlogIdByTitle("wrong"));
        BlogRecommendation addedRecommendation = db_dao.getAllBlogRecommendations().get(0);
        assertEquals("cba", addedRecommendation.getDescription());
    }

    @Test
    public void deleteBlogByTitle() {
        db_dao.createBlogRecommendation("google.fi", "Title", "Jane", "description");
        assertEquals(1, db_dao.getAllBlogRecommendations().size());
        db_dao.deleteBlogByTitle("title");
        assertFalse(db_dao.getAllBlogRecommendations().isEmpty());
        assertEquals(1, db_dao.getBlogIdByTitle("Title"));
        db_dao.deleteBlogByTitle("Title");
        assertEquals(0, db_dao.getAllBlogRecommendations().size());
    }

    @Test
    public void editingPodcastEditsCorrectly() {
        db_dao.createPodcastRecommendation("author", "title", "description", "name");
        db_dao.editPodcastRecommendation("title", "title", "Title");
        assertEquals(0, db_dao.getPodcastIdByTitle("title"));
        db_dao.editPodcastRecommendation("Title", "description", "good");
        assertEquals("good", db_dao.getAllPodcastRecommendations().get(0).getDescription());
    }

    @Test
    public void deletingPodcastDeletes() {
        db_dao.createPodcastRecommendation("author", "title", "description", "name");
        db_dao.deletePodcastByTitle("Title");
        assertEquals(1, db_dao.getPodcastIdByTitle("title"));
        db_dao.deletePodcastByTitle("title");
        assertTrue(db_dao.getAllPodcastRecommendations().isEmpty());
    }
}
