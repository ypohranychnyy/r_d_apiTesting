Feature: Manage Space Tags in ClickUp

  Scenario: Get all tags in a space
    Given I have a valid API token for ClickUp
    When I send a GET request to fetch all tags for the space
    Then the response status code should be 200
    And the list of tags should be returned

  Scenario: Create a new tag in a space
    Given I have a valid API token for ClickUp
    And I have a valid payload to create a new tag
    When I send a POST request to create the tag
    Then the response status code should be 200
    And the new tag should be present in the response

  Scenario: Update an existing tag in a space
    Given I have a valid API token for ClickUp
    And I have the ID of an existing tag
    And I have an updated payload for the tag
    When I send a PUT request to update the tag
    Then the response status code should be 200
    And the tag should be updated in the response

  Scenario: Delete a tag from a space
    Given I have a valid API token for ClickUp
    And I have the ID of the tag to delete
    When I send a DELETE request to remove the tag
    Then the response status code should be 200
    And the tag should no longer be available
