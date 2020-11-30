/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.domain;

/**
 *
 * @author anadis
 */
public class Timestamp {
    
    private String timestamp;
    private String comment;
    private int videoId;
    
    public Timestamp(String timestamp, String comment, int videoId) {
        this.comment = comment;
        this.timestamp = timestamp;
        this.videoId = videoId;
    }
    
    public String getTimestamp() {
        return this.timestamp;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public int getVideoId() {
        return this.videoId;
    }
}
