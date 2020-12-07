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
import recommendation_library.domain.TimeMemory;
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

    public boolean addTimeStampToVideo(String timestamp, String comment, String videoTitle) {

        try {
            return service.addTimeStampToVideo(timestamp, comment, videoTitle);
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

    public boolean timeStampAlreadyExists(String videoTitle, String time) {
        return service.timeStampAlreadyExists(videoTitle, time);
    }

    public List<String> listBooks() {
        List<BookRecommendation> list = service.getAllBookRecommendations();
        List<String> recommendationStrings = new ArrayList<>();
        int i = 1;

        for (BookRecommendation r : list) {
            recommendationStrings.add(System.lineSeparator() + "Book " + i++ + System.lineSeparator()
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
            /*List<TimeMemory> timeStampList = service.getTimestampsForVideo(r.getTitle());
            List<String> timeStampStrings = new ArrayList<>();
            
            timeStampList.forEach((t) -> {
                timeStampStrings.add("Time: " + t.getTimestamp() + ", "+ "Comment: " + t.getComment());
            });*/

            List<String> timeStampStrings = listTimestampsForVideo(r.getTitle());
            recommendationStrings.add(System.lineSeparator() + "Video " + i++ + System.lineSeparator()
                    + "Title: " + r.getTitle() + System.lineSeparator()
                    + "URL: " + r.getUrl() + System.lineSeparator()
                    + "Timestamps: " + System.lineSeparator() + timeStampStrings + System.lineSeparator()
                    + "Description: " + r.getDescription() + System.lineSeparator()
                    + "Added: " + r.getAddDate());
        }
        return recommendationStrings;
    }

    public List<String> listTimestampsForVideo(String videotitle) {
        List<TimeMemory> timeStampList = service.getTimestampsForVideo(videotitle);
        List<String> timeStampStrings = new ArrayList<>();

        timeStampList.forEach((t) -> {
            timeStampStrings.add("Time: " + t.getTimeStamp() + ", " + "Comment: " + t.getComment());
        });
        return timeStampStrings;
    }

    public List<String> listBlogs() {
        List<BlogRecommendation> list = service.getAllBlogRecommendations();
        List<String> recommendationStrings = new ArrayList<>();
        int i = 1;

        for (BlogRecommendation r : list) {
            recommendationStrings.add(System.lineSeparator() + "Blog " + i++ + System.lineSeparator()
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
            recommendationStrings.add(System.lineSeparator() + "Podcast " + i++ + System.lineSeparator()
                    + "Podcast name: " + r.getPodcastName() + System.lineSeparator()
                    + "Author: " + r.getAuthor() + System.lineSeparator()
                    + "Title: " + r.getTitle() + System.lineSeparator()
                    + "Description: " + r.getDescription() + System.lineSeparator()
                    + "Added: " + r.getAddDate());
        }

        return recommendationStrings;
    }

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

    public List<String> listBlogTitles() {
        List<BlogRecommendation> blogRecommendationList = service.getAllBlogRecommendations();
        List<String> blogTitleList = new ArrayList<>();

        for (BlogRecommendation book : blogRecommendationList) {
            blogTitleList.add(book.getTitle());
        }

        return blogTitleList;
    }

    public List<String> listPodcastTitles() {
        List<PodcastRecommendation> podcastRecommendationList = service.getAllPodcastRecommendations();
        List<String> podcastTitleList = new ArrayList<>();

        for (PodcastRecommendation book : podcastRecommendationList) {
            podcastTitleList.add(book.getTitle());
        }

        return podcastTitleList;
    }

    public boolean editBook(String titleToEdit, String fieldToEdit, String newValue) {

        try {
            this.service.editBookRecommendation(titleToEdit, fieldToEdit, newValue);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean editVideo(String titleToEdit, String fieldToEdit, String newValue) {

        try {
            this.service.editVideoRecommendation(titleToEdit, fieldToEdit, newValue);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean editBlog(String titleToEdit, String fieldToEdit, String newValue) {

        try {
            this.service.editBlogRecommendation(titleToEdit, fieldToEdit, newValue);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean editPodcast(String titleToEdit, String fieldToEdit, String newValue) {

        try {
            this.service.editPodcastRecommendation(titleToEdit, fieldToEdit, newValue);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean editTimestampForVideo(String videoTitle, String time_HH_MM_SS, String fieldToChange, String newValue) {

        try {
            this.service.editTimeStamp(videoTitle, time_HH_MM_SS, fieldToChange, newValue);
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

    public boolean deleteBlog(String titleToDelete) {
        return this.service.deleteBlogRecommendation(titleToDelete);
    }

    public boolean deletePodcast(String titleToDelete) {
        return this.service.deletePodcastRecommendation(titleToDelete);
    }

}
