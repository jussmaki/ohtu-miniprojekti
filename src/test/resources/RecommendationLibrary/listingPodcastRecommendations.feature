Feature: As a user I can list my podcast recommendations

    Scenario: user can list added podcast recommendations
        Given command add is selected
        When command podcast is selected
        And podcast recommendation with title "TitleTest", name "TestName", author "AuthorTest" and description "DescriptionTest" is added
		And command list is selected
        And command list podcasts is selected
        Then app lists a podcast recommendation with title "TitleTest", name "TestName", author "AuthorTest" and description "DescriptionTest"

    