/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jenni.makinen
 */
public class BlogRecommendation extends Recommendation {

    String url;
    String author;

    /**
     * Create blog object
     *
     * @param id
     * @param author
     * @param url
     * @param title
     * @param description
     * @param addDate
     */
    public BlogRecommendation(int id, String author, String url, String title, String description, String addDate) {
        super(id, title, Type.BLOG, description, addDate);
        this.url = url;
        this.author = author;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean setUrl(String newUrl) {
        url = newUrl;
        return true;
    }

    public String getAuthor() {
        return this.author;
    }

    public boolean setAuthor(String author) {
        this.author = author;
        return true;
    }

}
