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
    private String timeStamp;
    private String comment;
    private int videoId;
    
    public TimeMemory(int id, String timeStamp, String comment, int videoId) {
        this.id = id;
        this.comment = comment;
        this.timeStamp = timeStamp;
        this.videoId = videoId;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }
    
    public String getTimeStamp() {
        return this.timeStamp;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public int getVideoId() {
        return this.videoId;
    }
}
