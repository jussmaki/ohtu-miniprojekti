/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import recommendation_library.dao.DatabaseRecommendationDao;
import recommendation_library.dao.RecommendationDao;

/**
 *
 * @author anadis
 */
public class DatabaseService {

    private RecommendationDao dao;

    public DatabaseService(RecommendationDao dao) {
        this.dao = dao;
    }

    public boolean bookTitleAlreadyExists(String title) {
        List<BookRecommendation> books = dao.getAllBookRecommendations();
        for (BookRecommendation book : books) {
            if (book.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean videoTitleAlreadyExists(String title) {
        List<VideoRecommendation> videos = dao.getAllVideoRecommendations();
        for (VideoRecommendation video : videos) {
            if (video.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

/*  ON HOLD FOR dao.
    
    public boolean blogTitleAlreadyExists(String title) {
        List<BlogRecommendation> videos = dao.getAllBlogRecommendations();
        for (BlogRecommendation blog : blogs) {
            if (blog.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean podcastTitleAlreadyExists(String title) {
        List<PodcastRecommendation> videos = dao.getAllPodcastRecommendations();
        for (PodcastRecommendation podcast : podcasts) {
            if (podcast.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
*/
    /**
     * Create book recommendation
     *
     * @param author
     * @param title
     * @param isbn
     * @param pageCount
     * @param description
     * 
     * @return success/failure
     */
    public boolean addBook(String author, String title, String description, String isbn, int pageCount) {
        if (bookTitleAlreadyExists(title)) {
            return false;
        }
        dao.createBookRecommendation(author, title, description, isbn, pageCount);
        return true;
    }

    /**
     * Create video recommendation
     *
     * @param url
     * @param title
     * @param description
     * 
     * @return success/failure
     */
    public boolean addVideo(String url, String title, String description) {
        if (videoTitleAlreadyExists(title)) {
            return false;
        }
        dao.createVideoRecommendation(url, title, description);
        return true;
    }
    
    /**
     * Create blog recommendation
     *
     * @param url
     * @param author
     * @param title
     * @param description
     * 
     * @return success/failure
     */
    public boolean addBlog(String url, String title, String author, String description) {
//        if 
        dao.createBlogRecommendation(url, title, author, description);
        return true;
    }
    
    /**
     * Create blog recommendation
     *
     * @param author
     * @param title
     * @param description
     * @param name
     * 
     * @return success/failure
     */
    public boolean addPodcast(String author, String title, String description, String name) {
//        if (this) {
//            return false;
//        }
        dao.createPodcastRecommendation(author, title, description, name);
        return true;
    }

    public List<Recommendation> getAllRecommendationsSortedByCreatedDate() {
        List<Recommendation> recommendations = new ArrayList<>();
        for (Recommendation r : getAllBookRecommendations()) {
            recommendations.add(r);
        }
        for (Recommendation r : getAllVideoRecommendations()) {
            recommendations.add(r);
        }
        Collections.sort(recommendations);
        return recommendations;
    }

    public List<BookRecommendation> getAllBookRecommendations() {
        List<BookRecommendation> books = dao.getAllBookRecommendations();
        return books;
    }

    public List<VideoRecommendation> getAllVideoRecommendations() {
        List<VideoRecommendation> videos = dao.getAllVideoRecommendations();
        return videos;
    }

    public void editBookRecommendation(String title, String fieldToChange, String newValue) {
        this.dao.editBookRecommendation(title, fieldToChange, newValue);
    }
    
    public void editVideoRecommendation(String title, String fieldToChange, String newValue) {
        this.dao.editVideoRecommendation(title, fieldToChange, newValue);
    }

    public boolean deleteBookRecommendation(String title) {
        if (bookTitleAlreadyExists(title)) {
            dao.deleteBookByTitle(title);
            return true;
        }
        return false;
    }
    
    public boolean deleteVideoRecommendation(String title) {
        if (videoTitleAlreadyExists(title)) {
            dao.deleteVideoByTitle(title);
            return true;
        }
        return false;
    }
    
    public boolean addTimeStampToVideo(String timestamp, String comment, String videoTitle) {
        int id = dao.searchVideoByTitle(videoTitle);
        if (id == 0) {
            return false;
        }
        dao.addTimeStampToVideo(id, timestamp, comment);
        return true;
    }
    
    public List<TimeMemory> getTimestampsForVideo(String videoTitle) {
        List<TimeMemory> timestamps = new ArrayList<>();
        int id = dao.searchVideoByTitle(videoTitle);
        if (id == 0) {
            return timestamps;
        }
        for (TimeMemory timestamp : dao.getAllTimestampsForVideo(id)) {
            timestamps.add(timestamp);
        }
        return timestamps;
    }
    
}
