/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import recommendation_library.domain.BookRecommendation;

import java.util.*;
import java.util.function.Function;

import recommendation_library.domain.TimeMemory;
import recommendation_library.domain.Type;
import recommendation_library.domain.VideoRecommendation;

/**
 * @author jenni.makinen
 */


public class InMemoryRecommendationDao implements RecommendationDao {
    private List<BookRecommendation> bookRecommendations;
    private List<VideoRecommendation> videoRecommendations;


    public InMemoryRecommendationDao() {
        this.bookRecommendations = new ArrayList<>();
        this.videoRecommendations = new ArrayList<>();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TimeMemory> getAllTimestampsForVideo(int videId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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


}
