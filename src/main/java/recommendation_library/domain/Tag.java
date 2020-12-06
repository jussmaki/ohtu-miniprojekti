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
public class Tag {
    private int id;
    private String tagText;
    
    public Tag(int id, String tagText) {
        this.id = id;
        this.tagText = tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public int getId() {
        return id;
    }

    public String getTagText() {
        return tagText;
    }
    
}
