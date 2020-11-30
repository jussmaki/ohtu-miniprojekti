Feature: As a user I can delete a recommendation from the library
	
	@problem
    Scenario: user can delete a book recommendation
        Given command add is selected
        When book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
		And command delete is selected
		And book title "TitleTest" is entered
        Then app deletes a recommendation with the title "TitleTest"
		
	@problem
    Scenario: trying to delete a nonexistent book recommendation doesn't break the app
        Given command add is selected
        When book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
		And command delete is selected
		And book title "TitleTest2" is entered
        Then app doesn't delete a recommendation with the title "TitleTest"