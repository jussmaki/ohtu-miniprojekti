Feature: As a user I can list my blog recommendations

    Scenario: user can list added blog recommendations
        Given command add is selected
        When command blog is selected
        And blog recommendation with title "TitleTest", author "AuthorTest", description "DescriptionTest" and url "www.blog.fi" is added
		And command list is selected
        And command list blogs is selected
        Then app lists a recommendation with title "TitleTest", author "AuthorTest", description "DescriptionTest" and url "www.blog.fi"

    