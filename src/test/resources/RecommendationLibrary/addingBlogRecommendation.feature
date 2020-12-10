Feature: As a user I can add a new blog recommendation

Scenario: user can add a new blog recommendation
        Given command add is selected
        When command blog is selected
        When blog recommendation with title "TitleBlog", author "AuthorTest", description "DescriptionTest" and url "www.url.fi" is added
        Then system will respond with "Recommendation added"

Scenario: user cannot add new blog with title that is already taken
        Given blog recommendation with title "taken title" is added
        Given command add is selected
        When command blog is selected
        When blog recommendation with title "taken title", author "AuthorNew", description "different" and url "www.another.fi" is added
        Then system will respond with "Title already exists"
