##Feature: As a user I can edit a book recomendation already in the library

##        @problem
##    Scenario: user can edit an existing book recommendations
##        Given command add is selected
##        When book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
##		And command edit is selected
##		And book title "TitleTest" is entered
##            	And field to edit "author" is entered
##		And new value "newAuthor" for selected field is entered
##        Then value of the selected field "author" has been changed to "newAuthor"

## Jaana koittaa

Feature: As a user I can edit a book recomendation that already exists in the library

        @problem
    Scenario: user can edit an existing book recommendations
        Given command edit is selected
        When book command edit is selected
  		And book title "TitleTest" is entered
              	And field to edit "author" is entered
                And new value "newAuthor" for selected field is entered
        Then value of the selected field "author" has been changed to "newAuthor"
