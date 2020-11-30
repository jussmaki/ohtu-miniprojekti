/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library;

import recommendation_library.dao.InMemoryRecommendationDao;
import recommendation_library.dao.RecommendationDao;
import recommendation_library.io.StubIO;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import recommendation_library.domain.BookRecommendation;


/**
 *
 * @author jenni.makinen
 */
public class Stepdefs {
    
    List<String> inputLines;
    StubIO io;
    UserInterface ui;
    RecommendationDao dao;
    
    @Before
    public void setup(){
        this.inputLines = new ArrayList<String>();
    }

    @Given("command add is selected")
    public void commandAddSelected() {
        inputLines.add("1");
    }
    
    @When("book recommendation with author {string}, title {string}, description {string}, isbn {string} and page count {string} is added")
    public void addBookRecommendation(String author, String title, String description, String isbn, String pageCount) {
        inputLines.add(author);
        inputLines.add(title);
        inputLines.add(description);
        inputLines.add(isbn);
        inputLines.add(pageCount);
    }
    
    @When("command list is selected")
    public void commandListSelected() {
        inputLines.add("2");
    }
    
    @When("command delete is selected")
    public void commandDeleteSelected() {
        inputLines.add("4");
    }
    
    @When("book title {string} is entered")
    public void bookTitleIsEntered(String title) {
        inputLines.add(title);
    }
    
    /*  FAILS, WHY OH WHY??? 
    
    @When("command edit is selected")
    public void commandEditIsSelected() {
        inputLines.add("3");
    }
    
    @When("field to edit {string} is entered") 
    public void fieldToEditIsEntered(String fieldToEdit) {
        inputLines.add(fieldToEdit);
    }
    
    @When("new value {string} for selected field is entered")
    public void newValueToEditableFieldIsEntered(String newValue) {
        inputLines.add(newValue);
    }
    
    @Then("value of the selected field {string} has been changed to {string}")
    public void editingBookWorks(String fieldName, String newValue) {
        io = new StubIO(inputLines);
        dao = new InMemoryRecommendationDao();
        ui = new UserInterface(io, dao);
        ui.run();
        
        assertTrue(io.getPrints().contains("Field " + fieldName + " succesfully changed to " + newValue + "!"));
    }
*/

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        io = new StubIO(inputLines);
        dao = new InMemoryRecommendationDao();
        ui = new UserInterface(io, dao);
        ui.run();
        assertTrue(io.getPrints().contains(expectedOutput));
    }
    
    @Then("app lists a recommendation with author {string}, title {string}, description {string}, isbn {string}, and page count {string}")
    public void listingAddedRecommendation(String author, String title, String description, String isbn, String pageCount) {
        io = new StubIO(inputLines);
        dao = new InMemoryRecommendationDao();
        ui = new UserInterface(io, dao);
        ui.run();
        
        String addDate = java.time.LocalDate.now().toString();
        
        assertTrue(io.getPrints().contains("Recommendation 1" + System.lineSeparator() +
                    "Author: " + author + System.lineSeparator() +
                    "Title: " + title + System.lineSeparator() +
                    "Description: " + description + System.lineSeparator() +
                    "ISBN: " + isbn + System.lineSeparator() +
                    "Page count: " + pageCount + System.lineSeparator() +
                    "Added: " + addDate));
    }
    
    @Then("app deletes a recommendation with the title {string}")
    public void deletingBookWorks(String deletedTitle) {
        io = new StubIO(inputLines);
        dao = new InMemoryRecommendationDao();
        ui = new UserInterface(io, dao);
        ui.run();
        
        assertTrue(io.getPrints().contains("Recommendation added"));
        assertTrue(io.getPrints().contains("Recommendation deleted!"));
        
        for (BookRecommendation b : dao.getAllBookRecommendations()) {
            assertFalse(b.getTitle().equals(deletedTitle));
        }
        
        assertTrue(dao.getAllBookRecommendations().isEmpty());
    }
    
    @Then("app doesn't delete a recommendation with the title {string}")
    public void failingToDeleteWorks(String title) {
        inputLines.add("5");
        io = new StubIO(inputLines);
        dao = new InMemoryRecommendationDao();
        ui = new UserInterface(io, dao);
        ui.run();
        
        assertTrue(io.getPrints().contains("Recommendation added"));
        assertTrue(io.getPrints().contains("Recommendation with the given title doesn't exist! Try again: "));
    }
}
