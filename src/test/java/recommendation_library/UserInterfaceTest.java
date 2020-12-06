/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library;

/**
 * @author jhku
 */

import org.junit.Before;
import org.junit.Test;
import recommendation_library.dao.DatabaseRecommendationDao;
import recommendation_library.dao.RecommendationDao;
import recommendation_library.domain.BookRecommendation;
import recommendation_library.domain.DatabaseService;
import recommendation_library.domain.VideoRecommendation;
import recommendation_library.io.IO;
import recommendation_library.io.StubIO;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserInterfaceTest {

    UserInterface UI;
    IO input;
    DatabaseService service;
    RecommendationDao test_dao;
    RecommendationDao db_dao;
    DatabaseService db_service;
    StubIO db_io;
    UserInterface db_ui;

    @Before
    public void setUp() {
        input = mock(IO.class);
        test_dao = mock(RecommendationDao.class);
        service = mock(DatabaseService.class);
        UI = new UserInterface(input, test_dao);

        db_dao = new DatabaseRecommendationDao("src/test/resources/test.db");
        db_service = new DatabaseService(db_dao);

        List<BookRecommendation> books = db_dao.getAllBookRecommendations();
        for (BookRecommendation b : books) {
            db_dao.deleteBookByTitle(b.getTitle());
        }
        List<VideoRecommendation> videos = db_dao.getAllVideoRecommendations();
        for (VideoRecommendation v : videos) {
            db_dao.deleteVideoByTitle(v.getTitle());
        }
    }

    @Test
    public void checkInputCallsAdd() {
        when(input.nextLine())
            .thenReturn("1")
            .thenReturn("Jane")
            .thenReturn("Hobitti")
            .thenReturn("Sci-fi thriller")
            .thenReturn("1234-ABCD")
            .thenReturn("10")
            .thenReturn("0");


        UI.checkInput(1);


        verify(input, times(7)).nextLine();
        verify(test_dao, times(1)).createBookRecommendation("Jane", "Hobitti", "Sci-fi thriller", "1234-ABCD", 10);
        verify(input, times(8)).print(anyString());

    }

    @Test
    public void checkInputUnknownCommand() {
        UI.checkInput(999);

        verify(input, times(1)).print("Unknown command");
    }

    @Test
    public void checkInputCallsListBooks() {
        when(input.nextLine())
            .thenReturn("2");

        UI.checkInput(2);

        verify(test_dao, times(1)).getAllBookRecommendations();
    }

    @Test
    public void checkInputCallsListVideos() {
        when(input.nextLine())
            .thenReturn("3");

        UI.checkInput(2);

        verify(test_dao, times(1)).getAllVideoRecommendations();
    }

    @Test
    public void addBookAlreadyExistsWorks() {
        RecommendationApp app = mock(RecommendationApp.class);
        UserInterface ui = new UserInterface(input, app);

        // no need to test app's bookAlreadyExists implementation here, 
        // just return true to enter the if block
        when(app.bookAlreadyExists(anyString()))
            .thenReturn(true);

        when(input.nextLine())
            .thenReturn("Jane")
            .thenReturn("Hobitti")
            .thenReturn("Sci-fi thriller")
            .thenReturn("1234-ABCD")
            .thenReturn("10")
            .thenReturn("0");


        ui.addBook();

        verify(input).print("Title already exists");
    }

    @Test
    public void addBookAdditionFailedWorks() {
        RecommendationApp app = mock(RecommendationApp.class);
        UserInterface ui = new UserInterface(input, app);

        // no need to test app's bookAlreadyExists implementation here, 
        // just return true to enter the if block
        when(app.bookAlreadyExists(anyString()))
            .thenReturn(false);
        
        when(app.addBook(anyString(), anyString(), anyString(), anyString(), anyString(), anyList()))
            .thenReturn(false);

        when(input.nextLine())
            .thenReturn("Jane")
            .thenReturn("Hobitti")
            .thenReturn("Sci-fi thriller")
            .thenReturn("1234-ABCD")
            .thenReturn("10")
            .thenReturn("0");

        ui.addBook();

        verify(input).print("Addition failed");
    }

    @Test
    public void addVideoAlreadyExistsWorks() {
        RecommendationApp app = mock(RecommendationApp.class);
        UserInterface ui = new UserInterface(input, app);

        // no need to test app's bookAlreadyExists implementation here, 
        // just return true to enter the if block
        when(app.videoAlreadyExists(anyString()))
            .thenReturn(true);

        when(input.nextLine())
            .thenReturn("Jane")
            .thenReturn("Hobitti")
            .thenReturn("Sci-fi thriller")
            .thenReturn("0");


        ui.addVideo();

        verify(input).print("Title already exists");
    }

    @Test
    public void addVideoAdditionFailedWorks() {
        RecommendationApp app = mock(RecommendationApp.class);
        UserInterface ui = new UserInterface(input, app);

        // no need to test app's bookAlreadyExists implementation here, 
        // just return true to enter the if block
        when(app.videoAlreadyExists(anyString()))
            .thenReturn(false);

        when(app.addVideo(anyString(), anyString(), anyString(), anyList()))
            .thenReturn(false);

        when(input.nextLine()) 
            .thenReturn("Jane")
            .thenReturn("Hobitti")
            .thenReturn("Sci-fi thriller")
            .thenReturn("0");


        ui.addVideo();

        verify(input).print("Addition failed");
    }

    @Test
    public void editBookFieldnameDoesNotExistWorks() {
        RecommendationApp app = mock(RecommendationApp.class);
        UserInterface ui = new UserInterface(input, app);

        // imaginary book titles
        when(app.listBookTitles())
            .thenReturn(Arrays.asList("A", "B", "C"));

        // no need to test app's bookAlreadyExists implementation here, 
        // just return true to enter the if block
        when(app.bookAlreadyExists(anyString()))
            .thenReturn(true);

        when(input.nextLine())
            // titleToEdit
            .thenReturn("A")
            // fieldToEdit
            .thenReturn("FieldThatDoesNotExist")
            // correct field
            .thenReturn("1")
            // new value
            .thenReturn("D");

        ui.editBook();

        verify(input).print("Invalid input! \n[1] Author, [2] Title, [3] Description, [4] Isbn, [5] Pagecount");
    }

    @Test
    public void editBookTitleDoesNotExistWorks() {
        RecommendationApp app = mock(RecommendationApp.class);
        UserInterface ui = new UserInterface(input, app);

        // imaginary book titles
        when(app.listBookTitles())
            .thenReturn(Arrays.asList("A", "B", "C"));

        // no need to test app's bookAlreadyExists implementation here, 
        // just return true to enter the if block
        when(app.bookAlreadyExists(anyString()))
            .thenReturn(false);

        when(input.nextLine())
            // titleToEdit
            .thenReturn("A");

        ui.editBook();

        verify(input).print("Recommendation with the given title doesn't exist! Try again: ");
    }

    @Test
    public void editVideoFieldnameDoesNotExistWorks() {
        RecommendationApp app = mock(RecommendationApp.class);
        UserInterface ui = new UserInterface(input, app);

        // imaginary book titles
        when(app.listVideoTitles())
            .thenReturn(Arrays.asList("A", "B", "C"));

        // no need to test app's bookAlreadyExists implementation here, 
        // just return true to enter the if block
        when(app.videoAlreadyExists(anyString()))
            .thenReturn(true);

        when(input.nextLine())
            // titleToEdit
            .thenReturn("A")
            // fieldToEdit
            .thenReturn("FieldThatDoesNotExist")
            // correct fieldToEdit
            .thenReturn("1")
            // new value
            .thenReturn("D");

        ui.editVideo();

        verify(input).print("Invalid input! \n[1] Title, [2] URL, [3] Description");
    }

    @Test
    public void editVideoTitleDoesNotExistWorks() {
        RecommendationApp app = mock(RecommendationApp.class);
        UserInterface ui = new UserInterface(input, app);

        // imaginary book titles
        when(app.listVideoTitles())
            .thenReturn(Arrays.asList("A", "B", "C"));

        // no need to test app's bookAlreadyExists implementation here, 
        // just return true to enter the if block
        when(app.videoAlreadyExists(anyString()))
            .thenReturn(false);

        when(input.nextLine())
            // titleToEdit
            .thenReturn("A");

        ui.editVideo();

        verify(input).print("Recommendation with the given title doesn't exist! Try again: ");
    }

    @Test
    public void checkInputCallsListAll() {
        when(input.nextLine())
            .thenReturn("1");

        UI.checkInput(2);

        verify(test_dao, times(1)).getAllVideoRecommendations();
        verify(test_dao, times(1)).getAllBookRecommendations();

    }

    @Test
    public void listingRecommendationsReturnsList() {
        List<String> inputLines = Arrays.asList("1", "1", "Jeff VanderMeer", "Annihilation", "Good book", "ABCD", "777", "0", "2", "2");

        db_io = new StubIO(inputLines);
        db_ui = new UserInterface(db_io, db_dao);
        db_ui.run();

        assertTrue(db_io.getPrints().contains("Book 1" + System.lineSeparator()
            + "Author: Jeff VanderMeer" + System.lineSeparator()
            + "Title: Annihilation" + System.lineSeparator()
            + "Description: Good book" + System.lineSeparator()
            + "ISBN: ABCD" + System.lineSeparator()
            + "Page count: 777" + System.lineSeparator()
            + "Added: " + java.time.LocalDate.now().toString()));
    }

}
