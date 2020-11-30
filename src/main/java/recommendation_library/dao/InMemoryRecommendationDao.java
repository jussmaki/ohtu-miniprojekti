/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import recommendation_library.domain.BookRecommendation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import recommendation_library.domain.Type;
import recommendation_library.domain.VideoRecommendation;

/**
 *
 * @author jenni.makinen
 */


public class InMemoryRecommendationDao implements RecommendationDao {
    private List<BookRecommendation> bookRecommendations;
    

    public InMemoryRecommendationDao() {
        this.bookRecommendations = new ArrayList<>();
    }

    @Override
    public void createBookRecommendation(String author, String title, String description, String isbn, int pageCount) {
        String addDate = java.time.LocalDate.now().toString();
        this.bookRecommendations.add(new BookRecommendation(author, title, description, isbn, pageCount, addDate));
    }

    @Override
    public List<BookRecommendation> getAllBookRecommendations() {
        return this.bookRecommendations;
    }

    @Override
    public void editBookRecommendation(String title, String fieldToBeEdited, String newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VideoRecommendation> getAllVideoRecommendations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
