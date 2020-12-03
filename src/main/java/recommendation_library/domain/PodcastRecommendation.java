/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.domain;

/**
 *
 * @author jenni.makinen
 */
public class PodcastRecommendation extends Recommendation {
    
    String author;
    String podcastName;
    
    /**
     * Create podcast object
     *
     * @param id
     * @param author
     * @param title
     * @param description
     * @param podcastName
     * @param addDate
     */
    public PodcastRecommendation(int id, String author, String title, String description, String podcastName, String addDate) {
        super(id, title, Type.PODCAST, description, addDate);
        this.author = author;
        this.podcastName = podcastName;        
    }
    
    public String getAuthor() {
        return author;
    }

    public boolean setAuthor(String author) {
        this.author = author;
        return true;
    }
    
    public String getPodcastName() {
        return podcastName;
    }

    public boolean setPodcastName(String podcastName) {
        this.podcastName = podcastName;
        return true;
    }
    
}
