Feature: As a user I want to be able to see my book recommendations

	@problem
    Scenario: user can list added book recommendations
        Given command add is selected
        When book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
		And command list is selected
        Then app lists a recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest", and page count "10"