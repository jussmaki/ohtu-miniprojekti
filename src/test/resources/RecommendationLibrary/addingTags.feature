Feature: As a user I can add tags for recommendations

    Scenario: user can add a new tag for a book recommendation
        Given command add is selected
        When command book is selected
        And book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
        And command edit is selected
        And command book is selected
        And title "TitleTest" is entered
        And field to edit "6" is entered
        And tag "eka" is entered
        Then system will respond with "A new tag eka added!"

    Scenario: user can add a new tag for a video recommendation
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest" is added
        And command edit is selected
        And command video is selected
        And title "TitleTest" is entered
        And field to edit "5" is entered
        And tag "eka" is entered
        Then system will respond with "A new tag eka added!"


    Scenario: user can add a new tag for a blog recommendation
        Given command add is selected
        When command blog is selected
        When blog recommendation with title "TitleBlog", author "AuthorTest", description "DescriptionTest" and url "www.url.fi" is added
        And command edit is selected
        And command blog is selected
        And title "TitleBlog" is entered
        And field to edit "5" is entered
        And tag "eka" is entered
        Then system will respond with "A new tag eka added!"

    Scenario: user can add a new tag for a podcast recommendation
        Given command add is selected
        When command podcast is selected
        When podcast recommendation with title "podcastTitle", name "podcastName", author "AuthorTest" and description "DescriptionTest" is added
        And command edit is selected
        And command podcast is selected
        And title "podcastTitle" is entered
        And field to edit "5" is entered
        And tag "eka" is entered
        Then system will respond with "A new tag eka added!"

