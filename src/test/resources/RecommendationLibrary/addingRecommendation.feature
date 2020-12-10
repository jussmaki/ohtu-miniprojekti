Feature: As a user I can add a new recommendation

    Scenario: user can add a new book recommendation
        Given command add is selected
        When command book is selected
        When book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "9" is added
        Then system will respond with "Recommendation added"

    Scenario: user can add a new video recommendation
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest" is added
        Then system will respond with "Recommendation added"

    
    Scenario: user can add a new blog recommendation
        Given command add is selected
        When command blog is selected
        When blog recommendation with title "TitleBlog", author "AuthorTest", description "DescriptionTest" and url "www.url.fi" is added
        Then system will respond with "Recommendation added"

    Scenario: user cannot add new blog with title that is already taken
        Given blog recommendation with title "taken title" is added
        Given command add is selected
        When command blog is selected
        When blog recommendation with title "taken title", author "AuthorNew", description "different" and url "www.another.fi" is added
        Then system will respond with "Title already exists"

    Scenario: user can add a new podcast recommendation
        Given command add is selected
        When command podcast is selected
        When podcast recommendation with title "podcastTitle", name "podcastName", author "AuthorTest" and description "DescriptionTest" is added
        Then system will respond with "Recommendation added"

    Scenario: user cannot add new podcast with title that is already taken
        Given podcast recommendation with title "taken title" is added
        Given command add is selected
        When command podcast is selected
        When podcast recommendation with title "taken title", name "different name", author "AuthorNew" and description "different" is added
        Then system will respond with "Title already exists"
