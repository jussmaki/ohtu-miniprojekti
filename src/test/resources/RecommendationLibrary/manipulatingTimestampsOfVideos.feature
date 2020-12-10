Feature: as a user I can create, list and edit timestamps to my video recommendations

    Scenario: when adding a new video user can create a timestamps
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", timestamp "01:00" and comment "start here" is added
        Then system will respond with "Recommendation added"

    Scenario: user can list timestamps of a video
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", timestamp "01:00" and comment "start here" is added
        And command list is selected
        And command list videos is selected
        Then app lists a recommendation with title "TitleTest", timestamp "01:00" and comment "start here"

    Scenario: user can add a timestamp to an existing video
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", timestamp "01:00" and comment "start here" is added
        And command edit is selected
        And command video is selected
        And title "TitleTest" is entered
        And field to edit "4" is entered
        And field to edit "1" is entered
        And field to edit "2" is entered
        And new value "04:00" for selected field is entered
        And new value "end here" for selected field is entered
        Then system will respond with "Added successfully"
        
    Scenario: user can edit a timestamp 
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", timestamp "01:00" and comment "start here" is added
        And command edit is selected
        And command video is selected
        And title "TitleTest" is entered
        And field to edit "4" is entered
        And field to edit "2" is entered
        And new value "01:00" for selected field is entered
        And field to edit "2" is entered
        And new value "end here" for selected field is entered
        Then system will respond with "Field comment successfully changed to end here!"
