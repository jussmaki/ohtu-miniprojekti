Feature: As a user I can delete a book recommendation from the library
	
    Scenario: user can delete a book recommendation
        Given command add is selected
        When command book is selected
        And book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
		And command delete is selected
        And command book is selected
		And title "TitleTest" is entered
        Then app deletes a recommendation with the title "TitleTest"
		
    Scenario: trying to delete a nonexistent book recommendation doesn't break the app
        Given command add is selected
        When command book is selected
        And book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
		And command delete is selected
        And command book is selected
		And title "TitleTest2" is entered
        Then app doesn't delete a recommendation with the title "TitleTest"