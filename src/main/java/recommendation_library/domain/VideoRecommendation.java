package recommendation_library.domain;


import java.util.ArrayList;
import java.util.List;

public class VideoRecommendation extends Recommendation{

    String url;
    List<Timestamp> timestamps = new ArrayList<>();
    
    
    public VideoRecommendation(String url, String title, String description, String addDate) {
        super(title, Type.VIDEO, description, addDate);
        this.url = url;
    }
    
    public void addTimestamp(Timestamp timestamp) {
        this.timestamps.add(timestamp);
    }
    
    


}
