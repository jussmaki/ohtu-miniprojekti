Feature: As a user I can list my video recommendations

    Scenario: user can list added video recommendations
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest" is added
        And command list is selected
        And command list videos is selected
        Then app lists a recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest"

    Scenario: user can list all recommendations
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest" is added
        And command list is selected
        And command list all is selected
        Then app lists a recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest"