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
import recommendation_library.domain.Tag;
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
    void createTag(String tagText);
    
    void addTagToBook(int bookId, String tagText);
    void addTagToVideo(int videoId, String tagText);
    void addTagToBlog(int blogId, String tagText);
    void addTagToPodcast(int podcastId, String tagText);
    
    List<BookRecommendation> getAllBookRecommendations();
    List<VideoRecommendation> getAllVideoRecommendations();
    List<BlogRecommendation> getAllBlogRecommendations();
    List<PodcastRecommendation> getAllPodcastRecommendations();
    List<Tag> getAllTags();
    List<TimeMemory> getAllTimestampsForVideo(int videoId);
    List<Tag> getAllTagsForBook(int bookId);
    List<Tag> getAllTagsForVideo(int videoId);
    List<Tag> getAllTagsForBlog(int blogId);
    List<Tag> getAllTagsForPodcast(int podcastId);
            
    void editBookRecommendation(String title, String fieldToBeEdited, String newValue);
    void editVideoRecommendation(String title, String fieldToBeEdited, String newValue);
    void editTimestampForVideo(int videoId, int timeStampId, String fieldToBeEdited, String newValue);
    void editBlogRecommendation(String title, String fieldToBeEdited, String newValue);
    void editPodcastRecommendation(String title, String fieldToBeEdited, String newValue);
    
    void deleteBookByTitle(String title);
    void deleteTimestamp(int videoId, int timeStampId);
    void deleteVideoByTitle(String title);    
    void deleteBlogByTitle(String title);
    void deletePodcastByTitle(String title);
    
    int getVideoIdByTitle(String title);
    int getBookIdByTitle(String title);
    int getBlogIdByTitle(String title);
    int getPodcastIdByTitle(String title);
    
    int getTimestampIdByTitle(int videoId, String timestamp);
    void addTimeStampToVideo(int videoId, String timestamp, String comment);
    
    int getTagId(String tagText);
}
