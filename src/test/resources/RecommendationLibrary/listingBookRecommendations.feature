Feature: As a user I can list my book recommendations

    Scenario: user can list added book recommendations
        Given command add is selected
        When command book is selected
        And book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
		And command list is selected
        And command list books is selected
        Then app lists a recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest", and page count "10"

    Scenario: user can list all recommendations
        Given command add is selected
        When command book is selected
        And book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
        And command list is selected
        And command list all is selected
        Then app lists a recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest", and page count "10"