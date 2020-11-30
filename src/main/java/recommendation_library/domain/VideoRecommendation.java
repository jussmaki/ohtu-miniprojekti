package recommendation_library.domain;


import java.util.ArrayList;
import java.util.List;

public class VideoRecommendation extends Recommendation{

    String url;
    List<TimeMemory> timestamps = new ArrayList<>();
    
    
    public VideoRecommendation(String url, String title, String description, String addDate) {
        super(title, Type.VIDEO, description, addDate);
        this.url = url;
    }
    
    public void addTimestamp(TimeMemory timestamp) {
        this.timestamps.add(timestamp);
    }

    public String getUrl() {
        return url;
    }
    
    


}
