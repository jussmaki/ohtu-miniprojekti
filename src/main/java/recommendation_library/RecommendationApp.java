/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library;

import java.util.ArrayList;
import java.util.List;
import recommendation_library.dao.RecommendationDao;
import recommendation_library.domain.BlogRecommendation;
import recommendation_library.domain.BookRecommendation;
import recommendation_library.domain.DatabaseService;
import recommendation_library.domain.PodcastRecommendation;
import recommendation_library.domain.VideoRecommendation;
import recommendation_library.io.IO;

/**
 *
 * @author jhku
 */
public class RecommendationApp {

    private DatabaseService service;
    private IO io;

    public RecommendationApp(IO io, RecommendationDao dao) {
        this.io = io;
        this.service = new DatabaseService(dao);
    }

    public boolean addBook(String author, String title, String description, String isbn, String pageCount, List<String> tags) {

        try {
            int pageCountInt = Integer.parseInt(pageCount);
            return service.addBook(author, title, description, isbn, pageCountInt, tags);
        } catch (Exception e) {
            this.io.print("Given page count is not an integer!");
        }

        return false;
    }

    public boolean addVideo(String title, String description, String url, List<String> tags) {

        try {
            return service.addVideo(url, title, description, tags);
        } catch (Exception e) {
            this.io.print(e.getMessage());
        }

        return false;
    }
    
    public boolean addPodcast(String title, String podcastName, String author, String description, List<String> tags) {

        try {
            return service.addPodcast(author, title, description, podcastName, tags);
        } catch (Exception e) {
            this.io.print(e.getMessage());
        }

        return false;
    }
    
    public boolean addBlog(String title, String author, String description, String url, List<String> tags) {

        try {
            return service.addBlog(url, title, author, description, tags);
        } catch (Exception e) {
            this.io.print(e.getMessage());
        }

        return false;
    }

    public boolean bookAlreadyExists(String title) {
        return service.bookTitleAlreadyExists(title);
    }

    public boolean videoAlreadyExists(String title) {
        return service.videoTitleAlreadyExists(title);
    }
    
    public boolean blogAlreadyExists(String title) {
        return service.blogTitleAlreadyExists(title);
    }
    
    public boolean podcastAlreadyExists(String title) {
        return service.podcastTitleAlreadyExists(title);
    }

    public List<String> listBooks() {
        List<BookRecommendation> list = service.getAllBookRecommendations();
        List<String> recommendationStrings = new ArrayList<>();
        int i = 1;

        for (BookRecommendation r : list) {
            recommendationStrings.add("Book " + i++ + System.lineSeparator()
                    + "Author: " + r.getAuthor() + System.lineSeparator()
                    + "Title: " + r.getTitle() + System.lineSeparator()
                    + "Description: " + r.getDescription() + System.lineSeparator()
                    + "ISBN: " + r.getIsbn() + System.lineSeparator()
                    + "Page count: " + r.getPageCount() + System.lineSeparator()
                    + "Added: " + r.getAddDate());
        }

        return recommendationStrings;
    }

    public List<String> listVideos() {
        List<VideoRecommendation> list = service.getAllVideoRecommendations();
        List<String> recommendationStrings = new ArrayList<>();
        int i = 1;

        for (VideoRecommendation r : list) {
            recommendationStrings.add("Video " + i++ + System.lineSeparator()
                    + "Title: " + r.getTitle() + System.lineSeparator()
                    + "URL: " + r.getUrl() + System.lineSeparator()
                    + "Description: " + r.getDescription() + System.lineSeparator()
                    + "Added: " + r.getAddDate());
        }

        return recommendationStrings;
    }
    
    public List<String> listBlogs() {
        List<BlogRecommendation> list = service.getAllBlogRecommendations();
        List<String> recommendationStrings = new ArrayList<>();
        int i = 1;

        for (BlogRecommendation r : list) {
            recommendationStrings.add("Blog " + i++ + System.lineSeparator()
                    + "Author: " + r.getAuthor() + System.lineSeparator()
                    + "Title: " + r.getTitle() + System.lineSeparator()
                    + "Description: " + r.getDescription() + System.lineSeparator()
                    + "URL: " + r.getUrl() + System.lineSeparator()
                    + "Added: " + r.getAddDate());
        }

        return recommendationStrings;
    }
    
    public List<String> listPodcasts() {
        List<PodcastRecommendation> list = service.getAllPodcastRecommendations();
        List<String> recommendationStrings = new ArrayList<>();
        int i = 1;

        for (PodcastRecommendation r : list) {
            recommendationStrings.add("Podcast " + i++ + System.lineSeparator()
                    + "Podcast name: " + r.getPodcastName() + System.lineSeparator()
                    + "Author: " + r.getAuthor() + System.lineSeparator()
                    + "Title: " + r.getTitle() + System.lineSeparator()
                    + "Description: " + r.getDescription() + System.lineSeparator()
                    + "Added: " + r.getAddDate());
        }

        return recommendationStrings;
    }

    /**
     * list titles of all video recommendations contained within the library
     */
    public List<String> listVideoTitles() {
        List<VideoRecommendation> videoRecommendationList = service.getAllVideoRecommendations();
        List<String> videoTitleList = new ArrayList<>();

        for (VideoRecommendation video : videoRecommendationList) {
            videoTitleList.add(video.getTitle());
        }

        return videoTitleList;
    }

    public List<String> listBookTitles() {
        List<BookRecommendation> bookRecommendationList = service.getAllBookRecommendations();
        List<String> bookTitleList = new ArrayList<>();

        for (BookRecommendation book : bookRecommendationList) {
            bookTitleList.add(book.getTitle());
        }

        return bookTitleList;
    }

    public boolean editBook(String titleToEdit, String fieldToEdit, String newValue) {

        try {
            this.service.editBookRecommendation(titleToEdit, fieldToEdit, newValue);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean deleteBook(String titleToDelete) {
        return this.service.deleteBookRecommendation(titleToDelete);
    }

    public boolean deleteVideo(String titleToDelete) {
        return this.service.deleteVideoRecommendation(titleToDelete);
    }


    
    public boolean editVideo(String titleToEdit, String fieldToEdit, String newValue) {

        try {
            this.service.editVideoRecommendation(titleToEdit, fieldToEdit, newValue);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
