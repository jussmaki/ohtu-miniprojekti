/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import recommendation_library.domain.BookRecommendation;

import java.util.*;
import java.util.function.Function;
import recommendation_library.domain.BlogRecommendation;
import recommendation_library.domain.PodcastRecommendation;

import recommendation_library.domain.TimeMemory;
import recommendation_library.domain.VideoRecommendation;

/**
 * @author jenni.makinen
 */
public class InMemoryRecommendationDao implements RecommendationDao {

    private List<BookRecommendation> bookRecommendations;
    private List<VideoRecommendation> videoRecommendations;
    private List<TimeMemory> timeStamps;
    private List<BlogRecommendation> blogs;
    private List<PodcastRecommendation> podcasts;

    public InMemoryRecommendationDao() {
        this.bookRecommendations = new ArrayList<>();
        this.videoRecommendations = new ArrayList<>();
        this.timeStamps = new ArrayList<>();
        this.blogs = new ArrayList<>();
        this.podcasts = new ArrayList<>();
    }

    @Override
    public void createBookRecommendation(String author, String title, String description, String isbn, int pageCount) {
        String addDate = java.time.LocalDate.now().toString();
        this.bookRecommendations.add(new BookRecommendation(this.bookRecommendations.size() + 1, author, title, description, isbn, pageCount, addDate));
    }

    @Override
    public List<BookRecommendation> getAllBookRecommendations() {
        return this.bookRecommendations;
    }

    @Override
    public void editBookRecommendation(String title, String fieldToBeEdited, String newValue) {
        for (BookRecommendation b : bookRecommendations) {
            if (b.getTitle().equals(title)) {
                editBookMatchingField(b, fieldToBeEdited, newValue);
            }
        }
    }

    private void editBookMatchingField(BookRecommendation b, String fieldToBeEdited, String newValue) {
        fieldToBeEdited = fieldToBeEdited.toLowerCase();

        Map<String, Function<String, Boolean>> map = new HashMap<>();

        map.put("author", b::setAuthor);
        map.put("title", b::setTitle);
        map.put("description", b::setDescription);
        map.put("isbn", b::setIsbn);
        map.put("pagecount", s -> b.setPageCount(Integer.parseInt(s)));

        map.get(fieldToBeEdited).apply(newValue);
    }

    @Override
    public void deleteBookByTitle(String title) {
        BookRecommendation toBeRemoved = null;
        for (BookRecommendation b : bookRecommendations) {
            if (b.getTitle().equals(title)) {
                toBeRemoved = b;
                break;
            }
        }
        if (toBeRemoved != null) {
            bookRecommendations.remove(toBeRemoved);
        }
    }

    @Override
    public void createVideoRecommendation(String url, String title, String description) {
        String addDate = java.time.LocalDate.now().toString();
        videoRecommendations.add(new VideoRecommendation(this.videoRecommendations.size() + 1, url, title, description, addDate));
    }

    @Override
    public List<VideoRecommendation> getAllVideoRecommendations() {
        return this.videoRecommendations;
    }

    @Override
    public void editVideoRecommendation(String title, String fieldToBeEdited, String newValue) {
        for (VideoRecommendation v : videoRecommendations) {
            if (v.getTitle().equals(title)) {
                editVideoMatchingField(v, fieldToBeEdited, newValue);
            }
        }
    }

    private void editVideoMatchingField(VideoRecommendation v, String fieldToBeEdited, String newValue) {
        fieldToBeEdited = fieldToBeEdited.toLowerCase();
        Map<String, Function<String, Boolean>> map = new HashMap<>();
        map.put("title", v::setTitle);
        map.put("url", v::setUrl);
        map.put("description", v::setDescription);
        System.err.println(fieldToBeEdited);
        map.get(fieldToBeEdited).apply(newValue);
    }

    @Override
    public int searchVideoByTitle(String title) {
        for (VideoRecommendation v : videoRecommendations) {
            if (v.getTitle().equals(title)) {
                return v.getId();
            }
        }
        return 0;
    }

    @Override
    public void addTimeStampToVideo(int videoId, String timestamp, String comment) {
        this.timeStamps.add(new TimeMemory(this.timeStamps.size() + 1, timestamp, comment, videoId));
    }

    @Override
    public List<TimeMemory> getAllTimestampsForVideo(int videoId) {
        List<TimeMemory> stamps = new ArrayList<>();
        for (TimeMemory t : this.timeStamps) {
            if (t.getVideoId() == videoId) {
                stamps.add(t);
            }
        }
        return stamps;
    }

    @Override
    public void deleteVideoByTitle(String title) {
        VideoRecommendation toBeRemoved = null;
        for (VideoRecommendation v : videoRecommendations) {
            if (v.getTitle().equals(title)) {
                toBeRemoved = v;
                break;
            }
        }
        if (toBeRemoved != null) {
            videoRecommendations.remove(toBeRemoved);
        }
    }

    @Override
    public void editTimestampForVideo(int videoId, int timeStampId, String fieldToBeEdited, String newValue) {
        for (TimeMemory t : this.timeStamps) {
            if (t.getVideoId() == videoId && t.getId() == timeStampId) {
                editTimeStampField(t, fieldToBeEdited, newValue);
            }
        }
    }

    private void editTimeStampField(TimeMemory t, String fieldToBeEdited, String newValue) {
        if (fieldToBeEdited.toLowerCase().equals("comment")) {
            t.setComment(newValue);
        } else if (fieldToBeEdited.toLowerCase().equals("timestamp")) {
            t.setTimestamp(newValue);
        } else {
            System.err.println(fieldToBeEdited);
        }
    }

    @Override
    public void deleteTimestamp(int videoId, int timeStampId) {
        TimeMemory toBeRemoved = findTimestamp(videoId, timeStampId);

        if (toBeRemoved != null) {
            timeStamps.remove(toBeRemoved);
        }
    }

    private TimeMemory findTimestamp(int videoId, int timestampId) {
        TimeMemory toBeRemoved = null;
        for (TimeMemory t : this.timeStamps) {
            if (t.getVideoId() == videoId && t.getId() == timestampId) {
                toBeRemoved = t;
                return toBeRemoved;
            }
        }
        return null;
    }

    @Override
    public void createBlogRecommendation(String url, String title, String author, String description) {
        String addDate = java.time.LocalDate.now().toString();
        this.blogs.add(new BlogRecommendation(this.blogs.size() + 1, author, url, title, description, addDate));
    }

    @Override
    public void createPodcastRecommendation(String author, String title, String description, String podcastName) {
        String addDate = java.time.LocalDate.now().toString();
        this.podcasts.add(new PodcastRecommendation(this.podcasts.size() + 1, author, title, description, podcastName, addDate));
    }

    @Override
    public List<BlogRecommendation> getAllBlogRecommendations() {
        return this.blogs;
    }

    @Override
    public List<PodcastRecommendation> getAllPodcastRecommendations() {
        return this.podcasts;
    }

}
