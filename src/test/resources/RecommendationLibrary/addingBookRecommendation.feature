Feature: As a user I can add a new book recommendation

    Scenario: user can add a new book recommendation
        Given command add is selected
        When command book is selected
        When book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "9" is added
        Then system will respond with "Recommendation added"