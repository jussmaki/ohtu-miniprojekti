Feature: As a user I can edit a book recomendation already in the library

    Scenario: user can edit an existing book recommendations
        Given command add is selected
        When command book is selected
        And book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
		And command edit is selected
        And command book is selected
		And title "TitleTest" is entered
       	And field to edit "author" is entered
		And new value "newAuthor" for selected field is entered
		Then value of the selected field "author" has been changed to "newAuthor"