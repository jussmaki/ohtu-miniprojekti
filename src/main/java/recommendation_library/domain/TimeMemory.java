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
public class TimeMemory {
    
    private int id;
    private String timestamp;
    private String comment;
    private int videoId;
    
    public TimeMemory(int id, String timestamp, String comment, int videoId) {
        this.id = id;
        this.comment = comment;
        this.timestamp = timestamp;
        this.videoId = videoId;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
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
