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

    public boolean setId(int id) {
        this.id = id;
        return true;
    }

    public String getTitle() {
        return title;
    }

    public boolean setTitle(String title) {
        this.title = title;
        return true;
    }

    public Type getType() {
        return type;
    }

    public boolean setType(Type type) {
        this.type = type;
        return true;
    }

    public String getDescription() {
        return description;
    }

    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    public String getAddDate() {
        return addDate;
    }

    public boolean setAddDate(String addDate) {
        this.addDate = addDate;
        return true;
    }

    public boolean equals(Recommendation other) {
        return this.title.equals(other.getTitle()) && this.type == other.type;
    }

    @Override
    public int compareTo(Recommendation other) {
        return this.addDate.compareToIgnoreCase(other.getAddDate());
    }
    
    
}
