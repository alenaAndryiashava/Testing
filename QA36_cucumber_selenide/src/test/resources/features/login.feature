Feature: Login
  Some details about this features that might be interesting for other team members

  Scenario: Login to existing account
    Given that we navigate to home page
    * we accept cookies
    * we press Mein konto icon
    Then we see a login form
    When we enter valid creds of existing user
    And we submit this form
    Then Home page is loaded
    And we close the driver


