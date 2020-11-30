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
public class BookRecommendation extends Recommendation {
    
    String author;
    String isbn;
    int pageCount;

    public BookRecommendation(int id, String author, String title, String description, String isbn, int pageCount, String addDate) {
        super(id, title, Type.BOOK, description, addDate);
        this.author = author;
        this.isbn = isbn;
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
}
