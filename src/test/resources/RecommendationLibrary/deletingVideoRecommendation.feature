Feature: As a user I can delete a video recommendation from the library

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