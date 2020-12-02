/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.domain;


/**
 *
 * @author timot
 */
public class Recommendation implements Comparable<Recommendation> {
    protected int id;
    protected String title;
    protected Type type;
    protected String description;
    protected String addDate;
    
    public Recommendation(int id, String title, Type type, String description, String addDate) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.addDate = addDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public boolean equals(Recommendation other) {
        return this.title.equals(other.getTitle()) && this.type == other.type;
    }

    @Override
    public int compareTo(Recommendation other) {
        return this.addDate.compareToIgnoreCase(other.getAddDate());
    }
    
    
}
