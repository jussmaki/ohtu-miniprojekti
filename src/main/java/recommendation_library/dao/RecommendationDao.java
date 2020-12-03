/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import recommendation_library.domain.BookRecommendation;
import java.util.List;
import recommendation_library.domain.BlogRecommendation;
import recommendation_library.domain.PodcastRecommendation;
import recommendation_library.domain.TimeMemory;
import recommendation_library.domain.Type;
import recommendation_library.domain.VideoRecommendation;

/**
 *
 * @author anadis
 */
public interface RecommendationDao {
    
    void createBookRecommendation(String author, String title, String description, String isbn, int pageCount);
    void createVideoRecommendation(String url, String title, String description);
    void createBlogRecommendation(String url, String title, String author, String description);
    void createPodcastRecommendation(String author, String title, String description, String name);
    void addTimeStampToVideo(int videoId, String timestamp, String comment);
    
    List<BookRecommendation> getAllBookRecommendations();
    List<VideoRecommendation> getAllVideoRecommendations();
    List<BlogRecommendation> getAllBlogRecommendations();
    List<PodcastRecommendation> getAllPodcastRecommendations();
    List<TimeMemory> getAllTimestampsForVideo(int videoId);
            
    void editBookRecommendation(String title, String fieldToBeEdited, String newValue);
    void editVideoRecommendation(String title, String fieldToBeEdited, String newValue);
    void editTimestampForVideo(int videoId, int timeStampId, String fieldToBeEdited, String newValue);
    
    void deleteBookByTitle(String title);
    
    void deleteVideoByTitle(String title);
    int searchVideoByTitle(String title);
    
    void deleteTimestamp(int videoId, int timeStampId);
    
    public int findTimeStampId(int videoId, String timestamp);
}
