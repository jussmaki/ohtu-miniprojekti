
Feature: As a user I can edit a video recommendation already in the library

    Scenario: user can edit an existing video recommendations
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "http://urltest.com" is added
        And command edit is selected
        And command video is selected
        And title "TitleTest" is entered
        And field to edit "1" is entered
        And new value "newTitle" for selected field is entered
        Then value of the selected field "title" has been changed to "newTitle"
