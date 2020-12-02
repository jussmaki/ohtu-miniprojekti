/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library;

import java.util.ArrayList;
import java.util.List;
import recommendation_library.dao.RecommendationDao;
import recommendation_library.domain.BookRecommendation;
import recommendation_library.domain.DatabaseService;
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

    public boolean addBook(String author, String title, String description, String isbn, String pageCount) {

        try {
            int pageCountInt = Integer.parseInt(pageCount);
            return service.addBook(author, title, description, isbn, pageCountInt);
        } catch (Exception e) {
            this.io.print("Given page count is not an integer!");
        }

        return false;
    }

    public boolean addVideo(String title, String description, String url) {

        try {
            return service.addVideo(url, title, description);
        } catch (Exception e) {
            this.io.print("Given page count is not an integer!");
        }

        return false;
    }

    public boolean bookAlreadyExists(String title) {
        return service.bookTitleAlreadyExists(title);
    }

    public boolean videoAlreadyExists(String title) {
        return service.videoTitleAlreadyExists(title);
    }

    public List<String> listBooks() {
        List<BookRecommendation> list = service.getAllBookRecommendations();
        List<String> recommendationStrings = new ArrayList<>();
        int i = 1;

        for (BookRecommendation r : list) {
            recommendationStrings.add("Recommendation " + i++ + System.lineSeparator()
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
            recommendationStrings.add("Recommendation " + i++ + System.lineSeparator()
                    + "Title: " + r.getTitle() + System.lineSeparator()
                    + "URL: " + r.getUrl() + System.lineSeparator()
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

    public boolean delete(String titleToDelete) {
        return this.service.deleteBookRecommendation(titleToDelete);
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
