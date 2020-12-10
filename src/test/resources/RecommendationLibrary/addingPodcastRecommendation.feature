Feature: As a user I can add a new podcast recommendation

Scenario: user can add a new podcast recommendation
        Given command add is selected
        When command podcast is selected
        When podcast recommendation with title "podcastTitle", name "podcastName", author "AuthorTest" and description "DescriptionTest" is added
        Then system will respond with "Recommendation added"

Scenario: user cannot add new podcast with title that is already taken
        Given podcast recommendation with title "taken title" is added
        Given command add is selected
        When command podcast is selected
        When podcast recommendation with title "taken title", name "different name", author "AuthorNew" and description "different" is added
        Then system will respond with "Title already exists"
