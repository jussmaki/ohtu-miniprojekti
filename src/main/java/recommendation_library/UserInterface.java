/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library;

import java.util.*;

import recommendation_library.domain.Recommendation;
import recommendation_library.io.IO;
import recommendation_library.dao.RecommendationDao;
import recommendation_library.domain.BookRecommendation;

import recommendation_library.domain.DatabaseService;
import recommendation_library.RecommendationApp;

/**
 * @author jhku
 */
public class UserInterface {

    private IO io;
    private RecommendationApp recommendationApp;

    /**
     *
     * @param io
     * @param dao
     */
    public UserInterface(IO io, RecommendationDao dao) {
        this(io, new RecommendationApp(io, dao));
    }

    /**
     *
     * @param io
     * @param app
     */
    public UserInterface(IO io, RecommendationApp app) {
        this.io = io;
        this.recommendationApp = app;
    }

    /**
     * launch the application
     */
    public void run() {
        while (true) {
            io.print("");
            this.io.print("[1] Add recommendation, [2] List recommendations, [3] Edit recommendation, [4] Delete recommendation, [5] Exit");
            io.print("[10] Search recommendations by tag");
            io.print("");

            String input = io.nextLine();
            if (validateInput(input)) {
                int numericInput = Integer.valueOf(input);
                if (numericInput == 5) {
                    break;
                }
                checkInput(numericInput);
            }
        }

    }

    /**
     * Function to validate userInput
     *
     * @param userInput
     * @return true if userinput is numeric
     */
    private boolean validateInput(String userInput) {
        try {
            int input = Integer.valueOf(userInput);
            return true;
        } catch (Exception e) {
            this.io.print("Unknown command");
            return false;
        }
    }

    /**
     * @param input number given from user. 1 for "add", 2 for "list", 3 for
     * "edit", 4 for "delete", 5 for "exit"
     */
    public void checkInput(int input) {

        switch (input) {
            case 1:
                add();
                break;
            case 2:
                list();
                break;
            case 3:
                edit();
                break;
            case 4:
                delete();
                break;
            case 5:
                listTags();
                break;
            case 10:
                searchByTag();
                break;
            default:
                io.print("Unknown command");
        }
    }

    public void edit() {
        this.io.print("[1] Book, [2] Video, [3] Blog, [4] Podcast");
        String input = String.valueOf(io.nextLine());

        switch (input) {
            case "1":
                editBook();
                break;
            case "2":
                editVideo();
                break;
            case "3":
                editBlog();
                break;
            case "4":
                editPodcast();
                break;
            default:
                this.io.print("Unknown command");
        }
    }

    public void delete() {
        this.io.print("[1] Book, [2] Video, [3] Blog, [4] Podcast");
        String input = String.valueOf(io.nextLine());

        switch (input) {
            case "1":
                deleteBook();
                break;
            case "2":
                deleteVideo();
                break;
            case "3":
                deleteBlog();
                break;
            case "4":
                deletePodcast();
                break;
            default:
                this.io.print("Unknown command");
        }
    }

    public void add() {
        this.io.print("[1] Book, [2] Video, [3] Blog, [4] Podcast");
        String input = String.valueOf(io.nextLine());

        switch (input) {
            case "1":
                addBook();
                break;
            case "2":
                addVideo();
                break;
            case "3":
                addBlog();
                break;
            case "4":
                addPodcast();
                break;
            default:
                this.io.print("Unknown command");
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

            List<String> tags = new ArrayList<>();

            this.io.print("Type tags for the recommendation. Typing 0 will end the loop");
            while (true) {
                String tag = io.nextLine();
                if (tag.equals("0")) {
                    break;
                }
                tags.add(tag);
            }

            if (recommendationApp.addVideo(title, description, url, tags)) {
                this.io.print("Do you want to add a timestamp? [y]/[n]");
                if (this.io.nextLine().equals("y")) {
                    this.io.print("Time [hh:mm:ss]:");
                    String timestamp = io.nextLine();
                    this.io.print("Comment:");
                    String comment = io.nextLine();
                    recommendationApp.addTimeStampToVideo(timestamp, comment, title);
                }
                this.io.print("Recommendation added");
            } else {
                this.io.print("Addition failed");
            }
        } else {
            io.print("Title already exists");
        }
    }

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

            List<String> tags = new ArrayList<>();

            this.io.print("Type tags for the recommendation. Typing 0 will end the loop");
            while (true) {
                String tag = io.nextLine();
                if (tag.equals("0")) {
                    break;
                }
                tags.add(tag);
            }

            if (recommendationApp.addBook(author, title, description, isbn, pageCount, tags)) {
                this.io.print("Recommendation added");
            } else {
                this.io.print("Addition failed");
            }
        } else {
            io.print("Title already exists");
        }

    }

    public void addBlog() {

        this.io.print("Type the title of the blog recommendation");
        String title = io.nextLine();

        if (!recommendationApp.blogAlreadyExists(title)) {
            this.io.print("Type the author of the blog recommendation");
            String author = io.nextLine();

            this.io.print("Type the description of the blog recommendation");
            String description = io.nextLine();

            this.io.print("Type the URL of the blog recommendation");
            String url = io.nextLine();

            List<String> tags = new ArrayList<>();

            this.io.print("Type tags for the recommendation. Typing 0 will end the loop");
            while (true) {
                String tag = io.nextLine();
                if (tag.equals("0")) {
                    break;
                }
                tags.add(tag);
            }

            if (recommendationApp.addBlog(title, author, description, url, tags)) {
                this.io.print("Recommendation added");
            } else {
                this.io.print("Addition failed");
            }
        } else {
            io.print("Title already exists");
        }

    }

    public void addPodcast() {

        this.io.print("Type the title of the podcast recommendation");
        String title = io.nextLine();

        if (!recommendationApp.podcastAlreadyExists(title)) {
            this.io.print("Type the name of the podcast recommendation");
            String name = io.nextLine();

            this.io.print("Type the author of the podcast recommendation");
            String author = io.nextLine();

            this.io.print("Type the description of the podcast recommendation");
            String description = io.nextLine();

            List<String> tags = new ArrayList<>();

            this.io.print("Type tags for the recommendation. Typing 0 will end the loop");
            while (true) {
                String tag = io.nextLine();
                if (tag.equals("0")) {
                    break;
                }
                tags.add(tag);
            }

            if (recommendationApp.addPodcast(title, name, author, description, tags)) {
                this.io.print("Recommendation added");
            } else {
                this.io.print("Addition failed");
            }
        } else {
            io.print("Title already exists");
        }

    }

    public void list() {
        io.print("");
        this.io.print("[1] All, [2] Books, [3] Videos, [4] Blogs, [5] Podcasts, [6] Tags");
        io.print("");

        String input = String.valueOf(io.nextLine());

        switch (input) {
            case "1":
                listAll();
                break;
            case "2":
                listBooks();
                break;
            case "3":
                listVideos();
                break;
            case "4":
                listBlogs();
                break;
            case "5":
                listPodcasts();
                break;
            case "6":
                listTags();
                break;
            default:
                this.io.print("Unknown command");
        }
    }

    public void listBooks() {
        List<String> bookRecommendations = recommendationApp.listBooks();

        for (String bookRecommendation : bookRecommendations) {
            this.io.print(bookRecommendation);
        }
    }

    public void listVideos() {
        List<String> videoRecommendations = recommendationApp.listVideos();

        for (String videoRecommendation : videoRecommendations) {
            this.io.print(videoRecommendation);
        }
    }

    public void listBlogs() {
        List<String> blogRecommendations = recommendationApp.listBlogs();

        for (String blogRecommendation : blogRecommendations) {
            this.io.print(blogRecommendation);
        }
    }

    public void listPodcasts() {
        List<String> podcastRecommendations = recommendationApp.listPodcasts();

        for (String podcastRecommendation : podcastRecommendations) {
            this.io.print(podcastRecommendation);
        }
    }

    public void listTags() {
        Map<String, Integer> tagsCount = new HashMap<>();

        recommendationApp.listTags().forEach(tag -> tagsCount.put(tag, tagsCount.getOrDefault(tag, 0) + 1));

        List<Map.Entry<String, Integer>> tags = new ArrayList<>(tagsCount.entrySet());
        tags.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        for (var entry : tags) {
            io.print("\t" + String.format("%-" + 10 + "s", entry.getKey()) + " (used " + entry.getValue() + " times)");
        }
    }

    public void searchByTag() {

        io.print("Which tag do you want to search?");
        String tag = io.nextLine();

        searchTagsWithType(tag);
    }

    public void searchTagsWithType(String tag) {
        io.print("");
        io.print("[1] All, [2] Books, [3] Videos, [4] Blogs, [5] Podcasts");
        io.print("");
        String type = io.nextLine();
        List<String> recommendations = new ArrayList<>();
        switch (type) {
            case "1":
                recommendations = recommendationApp.listBooks(recommendationApp.getBooksWithTag(tag));
                recommendations.addAll(recommendationApp.listVideos(recommendationApp.getVideosWithTag(tag)));
                recommendations.addAll(recommendationApp.listBlogs(recommendationApp.getBlogsWithTag(tag)));
                recommendations.addAll(recommendationApp.listPodcasts(recommendationApp.getPodcastsWithTag(tag)));
                break;
            case "2":
                recommendations = recommendationApp.listBooks(recommendationApp.getBooksWithTag(tag));
                break;
            case "3":
                recommendations = recommendationApp.listVideos(recommendationApp.getVideosWithTag(tag));
                break;
            case "4":
                recommendations = recommendationApp.listBlogs(recommendationApp.getBlogsWithTag(tag));
                break;
            case "5":
                recommendations = recommendationApp.listPodcasts(recommendationApp.getPodcastsWithTag(tag));
                break;
        }

        recommendations.forEach(io::print);
    }

    /**
     * list all recommendations contained within the library
     */
    public void listAll() {
        listBooks();
        listVideos();
        listBlogs();
        listPodcasts();
    }

    public void editBook() {
        List<String> stringFieldNames = Arrays.asList("1", "2", "3", "4", "5", "6");

        this.io.print("Enter the title of the recommendation you wish to edit:\nTitles in your library:");
        List<String> allBookTitles = recommendationApp.listBookTitles();
        for (String title : allBookTitles) {
            this.io.print(title);
        };

        String titleToEdit = String.valueOf(io.nextLine());

        if (recommendationApp.bookAlreadyExists(titleToEdit)) {
            this.io.print("Choose a field to edit \n[1] Author, [2] Title, [3] Description, [4] Isbn, [5] Page count, [6] Add a tag");
            String fieldToEdit = String.valueOf(io.nextLine());

            while (!stringFieldNames.contains(fieldToEdit)) {
                this.io.print("Invalid input! \n[1] Author, [2] Title, [3] Description, [4] Isbn, [5] Page count, [6] Add a tag");
                fieldToEdit = String.valueOf(io.nextLine());
            }

            if (fieldToEdit.equals("6")) {
                this.io.print("Enter a new tag");
                String newTag = String.valueOf(io.nextLine());
                if (recommendationApp.addTagToBook(titleToEdit, newTag)) {
                    this.io.print("A new tag " + newTag + " added!");
                } else {
                    this.io.print("Failed!");
                }
            } else {
                this.io.print("Enter a new value to insert into the selected field");
                String newValue = String.valueOf(io.nextLine());

                switch (fieldToEdit) {
                    case "1":
                        fieldToEdit = "author";
                        break;
                    case "2":
                        fieldToEdit = "title";
                        break;
                    case "3":
                        fieldToEdit = "description";
                        break;
                    case "4":
                        fieldToEdit = "isbn";
                        break;
                    case "5":
                        fieldToEdit = "pagecount";
                        break;
                    default:
                        this.io.print("Unknown command");
                }
                if (recommendationApp.editBook(titleToEdit, fieldToEdit, newValue)) {
                    this.io.print("Field " + fieldToEdit + " successfully changed to " + newValue + "!");
                } else {
                    this.io.print("Failed!");
                }
            }
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

    public void editVideo() {
        List<String> stringFieldNames = Arrays.asList("1", "2", "3", "4", "5");
        List<String> stringFieldsTimestamp = Arrays.asList("1", "2");

        this.io.print("Enter the title of the recommendation you wish to edit:\nTitles in your library:");
        List<String> allVideoTitles = recommendationApp.listVideoTitles();
        for (String title : allVideoTitles) {
            this.io.print(title);
        }
        ;
        String titleToEdit = String.valueOf(io.nextLine());

        if (recommendationApp.videoAlreadyExists(titleToEdit)) {
            this.io.print("Choose a field to edit \n[1] Title, [2] URL, [3] Description, [4] Timestamp, [5] Add a tag");
            String fieldToEdit = String.valueOf(io.nextLine()).toLowerCase();

            while (!stringFieldNames.contains(fieldToEdit)) {
                this.io.print("Invalid input! \n[1] Title, [2] URL, [3] Description, [4] Timestamp, [5] Add a tag");
                fieldToEdit = String.valueOf(io.nextLine()).toLowerCase();
            }

            if (fieldToEdit.equals("5")) {
                this.io.print("Enter a new tag");
                String newTag = String.valueOf(io.nextLine());
                if (recommendationApp.addTagToVideo(titleToEdit, newTag)) {
                    this.io.print("A new tag " + newTag + " added!");
                } else {
                    this.io.print("Failed!");
                }
            } else {
                switch (fieldToEdit) {
                    case "1":
                        fieldToEdit = "title";
                        break;
                    case "2":
                        fieldToEdit = "url";
                        break;
                    case "3":
                        fieldToEdit = "description";
                        break;

                    case "4":

                        this.io.print("[1] Add timestamp, [2] Edit timestamp");
                        String input = io.nextLine();

                        if (input.equals("1")) {

                            this.io.print("Enter the time [HH:MM:SS] of the timestamp you wish to add:\nTimestamps of this recommendation:");

                            List<String> allVideoTimestamps = recommendationApp.listTimestampsForVideo(titleToEdit);
                            for (String stamp : allVideoTimestamps) {
                                this.io.print(stamp);
                            }

                            String time_HH_MM_SS = this.io.nextLine();

                            while (recommendationApp.timeStampAlreadyExists(titleToEdit, time_HH_MM_SS)) {
                                this.io.print("Given timestamp already exists! Try again or enter [e] to exit");
                                time_HH_MM_SS = this.io.nextLine();
                                if (time_HH_MM_SS.equals("e")) {
                                    return;
                                }
                            }

                            this.io.print("Enter comment for your timestamp");

                            String comment = this.io.nextLine();

                            if (recommendationApp.addTimeStampToVideo(time_HH_MM_SS, comment, titleToEdit)) {
                                this.io.print("Added successfully");
                                return;
                            } else {
                                this.io.print("Failed");
                            }

                        } else if (input.equals("2")) {

                            this.io.print("Enter the time [HH:MM:SS] of the timestamp you wish to edit:\nTimestamps of this recommendation:");
                            List<String> allVideoTimestamps = recommendationApp.listTimestampsForVideo(titleToEdit);
                            for (String stamp : allVideoTimestamps) {
                                this.io.print(stamp);
                            }
                            ;
                            String time_HH_MM_SS = this.io.nextLine();
                            while (!recommendationApp.timeStampAlreadyExists(titleToEdit, time_HH_MM_SS)) {
                                this.io.print("Given timestamp doesn't exist! Try again or enter [e] to exit");
                                time_HH_MM_SS = this.io.nextLine();
                                if (time_HH_MM_SS.equals("e")) {
                                    return;
                                }
                            }

                            this.io.print("Choose a field to edit \n[1] Time, [2] Comment");
                            fieldToEdit = String.valueOf(io.nextLine()).toLowerCase();
                            while (!stringFieldsTimestamp.contains(fieldToEdit)) {
                                this.io.print("Invalid input! \n[1] Time, [2] Comment");
                                fieldToEdit = String.valueOf(io.nextLine()).toLowerCase();
                            }

                            switch (fieldToEdit) {
                                case "1":
                                    fieldToEdit = "timestamp";
                                    break;
                                case "2":
                                    fieldToEdit = "comment";
                                    break;
                                default:
                                    this.io.print("Unknown command");
                            }

                            this.io.print("Enter a new value to insert into the selected field");
                            String newValue = String.valueOf(io.nextLine());
                            if (recommendationApp.editTimestampForVideo(titleToEdit, time_HH_MM_SS, fieldToEdit, newValue)) {
                                this.io.print("Field " + fieldToEdit + " successfully changed to " + newValue + "!");
                                return;
                            } else {
                                this.io.print("Failed!");
                            };
                        }

                    default:
                        this.io.print("Unknown command");
                }

                this.io.print("Enter a new value to insert into the selected field");
                String newValue = String.valueOf(io.nextLine());

                if (recommendationApp.editVideo(titleToEdit, fieldToEdit, newValue)) {
                    this.io.print("Field " + fieldToEdit + " successfully changed to " + newValue + "!");
                } else {
                    this.io.print("Failed!");
                }
            }
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

    public void editBlog() {
        List<String> stringFieldNames = Arrays.asList("1", "2", "3", "4", "5");

        this.io.print("Enter the title of the recommendation you wish to edit:\nTitles in your library:");
        List<String> allBlogTitles = recommendationApp.listBlogTitles();
        for (String title : allBlogTitles) {
            this.io.print(title);
        }
        ;
        String titleToEdit = String.valueOf(io.nextLine());

        if (recommendationApp.blogAlreadyExists(titleToEdit)) {
            this.io.print("Choose a field to edit \n[1] Title, [2] URL, [3] Description, [4] Author, [5] Add a tag");
            String fieldToEdit = String.valueOf(io.nextLine()).toLowerCase();

            while (!stringFieldNames.contains(fieldToEdit)) {
                this.io.print("Invalid input! \n[1] Title, [2] URL, [3] Description, [4] Author, [5] Add a tag");
                fieldToEdit = String.valueOf(io.nextLine()).toLowerCase();
            }

            if (fieldToEdit.equals("5")) {
                this.io.print("Enter a new tag");
                String newTag = String.valueOf(io.nextLine());
                if (recommendationApp.addTagToBlog(titleToEdit, newTag)) {
                    this.io.print("A new tag " + newTag + " added!");
                } else {
                    this.io.print("Failed!");
                }
            } else {

                this.io.print("Enter a new value to insert into the selected field");
                String newValue = String.valueOf(io.nextLine());

                switch (fieldToEdit) {
                    case "1":
                        fieldToEdit = "title";
                        break;
                    case "2":
                        fieldToEdit = "url";
                        break;
                    case "3":
                        fieldToEdit = "description";
                        break;
                    case "4":
                        fieldToEdit = "author";
                        break;
                    default:
                        this.io.print("Unknown command");
                }

                if (recommendationApp.editBlog(titleToEdit, fieldToEdit, newValue)) {
                    this.io.print("Field " + fieldToEdit + " successfully changed to " + newValue + "!");
                } else {
                    this.io.print("Failed!");
                }
            }
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

    public void editPodcast() {
        List<String> stringFieldNames = Arrays.asList("1", "2", "3", "4", "5");

        this.io.print("Enter the title of the recommendation you wish to edit:\nTitles in your library:");
        List<String> allPodcastTitles = recommendationApp.listPodcastTitles();
        for (String title : allPodcastTitles) {
            this.io.print(title);
        }
        ;
        String titleToEdit = String.valueOf(io.nextLine());

        if (recommendationApp.podcastAlreadyExists(titleToEdit)) {
            this.io.print("Choose a field to edit \n[1] Title, [2] Author, [3] Description, [4] Podcast name, [5] Add a tag");
            String fieldToEdit = String.valueOf(io.nextLine()).toLowerCase();

            while (!stringFieldNames.contains(fieldToEdit)) {
                this.io.print("Invalid input! \n[1] Title, [2] Author, [3] Description, [4] Podcast name, [5] Add a tag");
                fieldToEdit = String.valueOf(io.nextLine()).toLowerCase();
            }

            if (fieldToEdit.equals("5")) {
                this.io.print("Enter a new tag");
                String newTag = String.valueOf(io.nextLine());
                if (recommendationApp.addTagToBlog(titleToEdit, newTag)) {
                    this.io.print("A new tag " + newTag + " added!");
                } else {
                    this.io.print("Failed!");
                }
            } else {
                this.io.print("Enter a new value to insert into the selected field");
                String newValue = String.valueOf(io.nextLine());

                switch (fieldToEdit) {
                    case "1":
                        fieldToEdit = "title";
                        break;
                    case "2":
                        fieldToEdit = "author";
                        break;
                    case "3":
                        fieldToEdit = "description";
                        break;
                    case "4":
                        fieldToEdit = "name";
                        break;
                    default:
                        this.io.print("Unknown command");
                }

                if (recommendationApp.editPodcast(titleToEdit, fieldToEdit, newValue)) {
                    this.io.print("Field " + fieldToEdit + " successfully changed to " + newValue + "!");
                } else {
                    this.io.print("Failed!");
                }
            }
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

    public void deleteBook() {
        this.io.print("Enter the title of the recommendation you wish to delete:\nTitles in your library:");
        List<String> allBookTitles = recommendationApp.listBookTitles();
        for (String title : allBookTitles) {
            this.io.print(title);
        }
        ;
        String titleToDelete = String.valueOf(io.nextLine());

        if (recommendationApp.deleteBook(titleToDelete)) {
            this.io.print("Recommendation deleted!");
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

    public void deleteVideo() {
        this.io.print("Enter the title of the recommendation you wish to delete:\nTitles in your library:");
        List<String> allVideoTitles = recommendationApp.listVideoTitles();
        for (String title : allVideoTitles) {
            this.io.print(title);
        }
        ;
        String titleToDelete = String.valueOf(io.nextLine());

        if (recommendationApp.deleteVideo(titleToDelete)) {
            this.io.print("Recommendation deleted!");
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

    public void deleteBlog() {
        this.io.print("Enter the title of the recommendation you wish to delete:\nTitles in your library:");
        List<String> allBlogTitles = recommendationApp.listBlogTitles();
        for (String title : allBlogTitles) {
            this.io.print(title);
        }
        ;
        String titleToDelete = String.valueOf(io.nextLine());

        if (recommendationApp.deleteBlog(titleToDelete)) {
            this.io.print("Recommendation deleted!");
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

    public void deletePodcast() {
        this.io.print("Enter the title of the recommendation you wish to delete:\nTitles in your library:");
        List<String> allPodcastTitles = recommendationApp.listPodcastTitles();
        for (String title : allPodcastTitles) {
            this.io.print(title);
        }
        ;
        String titleToDelete = String.valueOf(io.nextLine());

        if (recommendationApp.deletePodcast(titleToDelete)) {
            this.io.print("Recommendation deleted!");
        } else {
            this.io.print("Recommendation with the given title doesn't exist! Try again: ");
        }
    }

}
