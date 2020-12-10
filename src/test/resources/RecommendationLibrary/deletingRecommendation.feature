Feature: As a user I can delete a recommendation from the library
	
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

    Scenario: user can delete a video recommendation
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest" is added
        And command delete is selected
        And command video is selected
        And title "TitleTest" is entered
        Then app deletes a recommendation with the title "TitleTest"

    Scenario: trying to delete a nonexistent video recommendation doesn't break the app
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest" is added
        And command delete is selected
        And command video is selected
        And title "TitleTest2" is entered
        Then app doesn't delete a recommendation with the title "TitleTest"

    Scenario: user can delete a blog recommendation
        Given command add is selected
        When command blog is selected
        And blog recommendation with title "TitleBlog", author "AuthorTest", description "DescriptionTest" and url "www.url.fi" is added
        And command delete is selected
        And command blog is selected
		And title "TitleBlog" is entered
        Then app deletes a recommendation with the title "TitleBlog"
	
    Scenario: user can delete a podcast recommendation
        Given command add is selected
        When command podcast is selected
        And podcast recommendation with title "title of podcast", name "podcastName", author "AuthorTest" and description "DescriptionTest" is added
        And command delete is selected
        And command podcast is selected
		And title "title of podcast" is entered
        Then app deletes a recommendation with the title "title of podcast"
	