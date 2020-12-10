Feature: As a user I can edit a recomendation already in the library

    Scenario: user can edit an existing book recommendations
        Given command add is selected
        When command book is selected
        And book recommendation with author "AuthorTest", title "TitleTest", description "DescriptionTest", isbn "isbnTest" and page count "10" is added
		And command edit is selected
        And command book is selected
		And title "TitleTest" is entered
       	And field to edit "author" is entered
       	And field to edit "1" is entered
		And new value "newAuthor" for selected field is entered
		Then value of the selected field "author" has been changed to "newAuthor"

    Scenario: user cannot edit nonexisting book
        Given command edit is selected
        And command book is selected
		And title "TitleTest" is entered
        Then system will respond with "Recommendation with the given title doesn't exist! Try again: "

    Scenario: user can edit title of an existing video recommendations
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "http://urltest.com" is added
        And command edit is selected
        And command video is selected
        And title "TitleTest" is entered
        And field to edit "title" is entered
        And field to edit "1" is entered
        And new value "newTitle" for selected field is entered
        Then value of the selected field "title" has been changed to "newTitle"

    Scenario: user can edit url an existing video recommendations
        Given command add is selected
        When command video is selected
        And video recommendation with title "TitleTest", description "DescriptionTest" and url "http://urltest.com" is added
        And command edit is selected
        And command video is selected
        And title "TitleTest" is entered
        And field to edit "2" is entered
        And new value "www.youtube.fi" for selected field is entered
        Then value of the selected field "url" has been changed to "www.youtube.fi"

    Scenario: user can edit title of an an existing blog recommendations
        Given command add is selected
        When command blog is selected
        And blog recommendation with title "TitleBlog", author "AuthorTest", description "DescriptionTest" and url "www.url.fi" is added
        And command edit is selected
        And command blog is selected
		And title "TitleBlog" is entered
       	And field to edit "1" is entered
		And new value "new title" for selected field is entered
		Then value of the selected field "title" has been changed to "new title"

    Scenario: user can edit url of an an existing blog recommendations
        Given command add is selected
        When command blog is selected
        And blog recommendation with title "TitleBlog", author "AuthorTest", description "DescriptionTest" and url "www.url.fi" is added
        And command edit is selected
        And command blog is selected
		And title "TitleBlog" is entered
        And field to edit "url" is entered
       	And field to edit "2" is entered
		And new value "www.newblog.fi" for selected field is entered
		Then value of the selected field "url" has been changed to "www.newblog.fi"

    Scenario: user can edit title of an existing podcast recommendations
        Given command add is selected
        When command podcast is selected
        And podcast recommendation with title "podcastTitle", name "podcastName", author "AuthorTest" and description "DescriptionTest" is added
        And command edit is selected
        And command podcast is selected
		And title "podcastTitle" is entered
       	And field to edit "1" is entered
		And new value "new title" for selected field is entered
		Then system will respond with "Field title successfully changed to new title!"

    Scenario: user can edit the description of an existing podcast recommendations
        Given command add is selected
        When command podcast is selected
        And podcast recommendation with title "podcastTitle", name "podcastName", author "AuthorTest" and description "DescriptionTest" is added
        And command edit is selected
        And command podcast is selected
		And title "podcastTitle" is entered
        And field to edit "description" is entered
       	And field to edit "3" is entered
		And new value "new description" for selected field is entered
		Then system will respond with "Field description successfully changed to new description!"

    Scenario: user can edit the author of an existing podcast recommendations
        Given command add is selected
        When command podcast is selected
        And podcast recommendation with title "podcastTitle", name "podcastName", author "AuthorTest" and description "DescriptionTest" is added
        And command edit is selected
        And command podcast is selected
		And title "podcastTitle" is entered
        And field to edit "description" is entered
       	And field to edit "2" is entered
		And new value "different" for selected field is entered
		Then system will respond with "Field author successfully changed to different!"
