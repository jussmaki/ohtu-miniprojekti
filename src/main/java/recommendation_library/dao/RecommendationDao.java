/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import recommendation_library.domain.BookRecommendation;
import java.util.List;
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
    void addTimeStampToVideo(int videoId, String timestamp, String comment);
    
    List<BookRecommendation> getAllBookRecommendations();
    List<VideoRecommendation> getAllVideoRecommendations();
    List<TimeMemory> getAllTimestampsForVideo(int videId);
            
    void editBookRecommendation(String title, String fieldToBeEdited, String newValue);
    void editVideoRecommendation(String title, String fieldToBeEdited, String newValue);
    
    void deleteBookByTitle(String title);
    
    void deleteVideoByTitle(String title);
    int searchVideoByTitle(String title);
}
