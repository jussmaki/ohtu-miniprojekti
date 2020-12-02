/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library;

import java.util.ArrayList;
import java.util.Arrays;
import recommendation_library.io.IO;
import recommendation_library.dao.RecommendationDao;
import recommendation_library.domain.BookRecommendation;
import java.util.List;
import recommendation_library.domain.DatabaseService;
import recommendation_library.RecommendationApp;

/**
 *
 * @author jhku
 */
public class UserInterface {

    private IO io;
    private RecommendationApp recommendationApp;

    public UserInterface(IO io, RecommendationDao dao) {
        this.io = io;
        this.recommendationApp = new RecommendationApp(io, dao);
    }

    /**
     * launch the application
     */
    public void run() {
        while (true) {
            this.io.print("[1] Add recommendation, [2] List recommendations, [3] Edit recommendation, [4] Delete recommendation, [5] Exit");
            int input = Integer.valueOf(io.nextLine());
            if (input == 5) {
                break;
            }
            checkInput(input);
        }

    }

    /**
     *
     * @param input number given from user. 1 for "add", 2 for "list", 3 for
     * "edit", 4 for "exit"
     */
    public void checkInput(int input) {
        if (input == 1) {
            add();
        } else if (input == 2) {
            list();
        } else if (input == 3) {
            edit();
        } else if (input == 4) {
            delete();
        } else {
            this.io.print("Unknown command");
        }
    }

    public void add() {
        this.io.print("[1] Add book, [2] Add video");
        int input = Integer.valueOf(io.nextLine());

        if (input == 1) {
            addBook();
        } else if (input == 2) {
            addVideo();
        }

    }

    public void addVideo() {

        this.io.print("Type the title of the video recommendation");
        String title = io.nextLine();

        if (!recommendationApp.videoAlreadyExists(title)) {
            this.io.print("Type the description of the video recommendation");
            String description = io.nextLine();

            this.io.print("Type the url of the video recommendation");
            String url = io.nextLine();

            if (recommendationApp.addVideo(title, description, url)) {
                this.io.print("Recommendation added");
            } else {
                this.io.print("Addition failed");
            }
        } else {
            System.out.println("Title already exists");
        }
    }

    /**
     * add a book recommendation to the library
     */
    public void addBook() {

        this.io.print("Type the author of the book recommendation");
        String author = io.nextLine();

        this.io.print("Type the title of the book recommendation");
        String title = io.nextLine();

        if (!recommendationApp.bookAlreadyExists(title)) {
            this.io.print("Type the description of the book recommendation");
            String description = io.nextLine();

            this.io.print("Type the ISBN of the book recommendation");
            String isbn = io.nextLine();

            this.io.print("Type the page count of the book recommendation");
            String pageCount = io.nextLine();

            if (recommendationApp.addBook(author, title, description, isbn, pageCount)) {
                this.io.print("Recommendation added");
            } else {
                this.io.print("Addition failed");
            }
        } else {
            System.out.println("Title already exists");
        }

    }

    public void list() {
        this.io.print("[1] List all, [2] List books, [3] List videos");
        int input = Integer.valueOf(io.nextLine());

        if (input == 1) {
            listAll();
        } else if (input == 2) {
            listBooks();
        } else if(input == 3){
            listVideos();
        }
    }
    
    public void listBooks(){
        List<String> bookRecommendations = recommendationApp.listBooks();
        
        for(String bookRecommendation : bookRecommendations){
            this.io.print(bookRecommendation);
        }
    }

    /**
     * list all book recommendations contained within the library
     */
    public void listAll() {
        List<String> recommendations = recommendationApp.list();

        for (String recommendation : recommendations) {
            this.io.print(recommendation);
        }
    }

    /**
     * edit a book recommendation TODO: pagecount is integer, so now this can
     * only edit string fields -> should we change the field "pagecount" into
     * String in database to ease the job? TODO: should offer an option to exit
     * if given title or fieldname doesn't exist, and perhaps an option to list
     * recommendations(?)
     */
    public void edit() {
        List<String> stringFieldNames = Arrays.asList("author", "title", "description", "isbn");

        this.io.print("Enter the title of the recommendation you wish to edit:\nTitles in your library:");
        List<String> allTitles = recommendationApp.listTitles();
        for (String title : allTitles) {
            this.io.print(title);
        };
        String titleToEdit = String.valueOf(io.nextLine());

        if (recommendationApp.bookAlreadyExists(titleToEdit)) {
            this.io.print("Enter the fieldname of the selected recommendation you wish to edit (author, title, description, isbn, pagecount):");
            String fieldToEdit = String.valueOf(io.nextLine());

            while (!stringFieldNames.contains(fieldToEdit)) {
                this.io.print("Given fieldname doesn't exist! Enter a valid fieldname (author, title, description, isbn, pagecount):");
                fieldToEdit = String.valueOf(io.nextLine());
            }

            this.io.print("Enter a new value to insert into the selected field:");
            String newValue = String.valueOf(io.nextLine());

            if (recommendationApp.edit(titleToEdit, fieldToEdit, newValue)) {
                this.io.print("Field " + fieldToEdit + " succesfully changed to " + newValue + "!");
            } else {
                this.io.print("Failed!");
            }

        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

    public void delete() {
        this.io.print("Enter the title of the recommendation you wish to delete:\nTitles in your library:");
        List<String> allTitles = recommendationApp.listTitles();
        for (String title : allTitles) {
            this.io.print(title);
        };
        String titleToDelete = String.valueOf(io.nextLine());

        if (recommendationApp.delete(titleToDelete)) {
            this.io.print("Recommendation deleted!");
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

}
