Feature: As a user I can add a new video recommendation

    Scenario: user can add a new video recommendation
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "urlTest" is added
        Then system will respond with "Recommendation added"