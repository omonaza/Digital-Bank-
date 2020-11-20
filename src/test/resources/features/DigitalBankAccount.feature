Feature: home page

  description

  Background: Login
    Given User navigates to Digital Bank login page
    And Verify that web title is "Digital Bank"
    When User logs in with following credentials
      | username              | password  |
      | JohnDoe@testemail.com | Password1 |
    Then Verify that web title is "Digital Bank"
  @logsIn
  Scenario: As a user, I want to be able to view all details in my account home page

    And Verify that "Welcome John" text is displayed
    And Verify that panel with account information is displayed
    #replace below steps with one step above to make less code
#    And Verify that "Home" button is displayed
#    And Verify that "Account Balance Summary" text is displayed
#    And Verify that "Deposit vs. Withdraw (Last 3 Months)" text is displayed
#    And Verify that "Withdraw By Category (Last 3 Months)" text is displayed
#    And Verify that "Deposit By Category (Last 3 Months)" text is displayed
#    And Verify that under "Banking Accounts" we have options
#      | Checking | Savings | Transactions |
#    And Verify that under "Credit Accounts" we have options
#      | Digital Credit |
#    And Verify that under "Transfer/Payments" we have options
#      | Transfer |
#    And Verify that under "3rd Party Transfers" we have options
#      | Visa Direct |


  Scenario:   As a User, I want have the ability to create a new Checking bank account so that my payment can be processed from my preferred account.

    And Verify that "Welcome John" text is displayed

    And User clicks on "Checking" button
    And Verify that under "Checking" we have options
      | View Checking | New Checking |

  Scenario: Checking account page validations

    And User clicks on Checking button
    And User clicks on New Checking button
    And Verify that "New Checking Account" header is displayed
    And Verify that "Select Checking Account Type" label is displayed
    And Verify that radio buttons are unchecked with following text
      | Standard Checking | Interest Checking |
    And Verify that "Select Account Ownership" label is displayed
    And Verify that radio buttons are unchecked with following text
      | Individual | Joint |
    And Verify that "Account Name" label is displayed
    And Verify that input field accepts alphanumeric and special characters
    And Verify that "Initial Deposit Amount" text is displayed
    And Verify that input field accepts accepts numeric whole or decimal values
    And Verify that "ex. Minimum opening deposit is $25.00" text is displayed
    And Verify that "Submit" button is displayed
    And Verify that "Reset" button is displayed


  Scenario: As a User, I get error message when submit empty fields

    And User clicks on Checking button
    And User clicks on New Checking button
    And Verify that "New Checking Account" text is displayed
    And User clicks on "Submit" button
    And User should get error message "Please select one of these options."


  Scenario: As a User, I get error message when submit empty fields on Select Account Ownership

    And User clicks on Checking button
    And User clicks on New Checking button
    And Verify that "New Checking Account" text is displayed
    And User clicks on "Standard Checking" radio buttons
    And User clicks on "Submit" button
    And User should get error message "Please select one of these options."


  Scenario: As a User, I get error message when submit empty fields on Account Name

    And User clicks on Checking button
    And User clicks on New Checking button
    And Verify that "New Checking Account" text is displayed
    And User clicks on "Standard Checking" radio buttons
    And User clicks on "Individuals" radio buttons
    And User clicks on "Submit" button
    And User should get error message "Please fill out this field."


  Scenario: As a User, I get error message when submit empty fields on Initial Deposit Amount

    And User clicks on Checking button
    And User clicks on New Checking button
    And Verify that "New Checking Account" text is displayed
    And User clicks on "Standard Checking" radio buttons
    And User clicks on "Individuals" radio buttons
    And User enter name "Marry" into Account Name
    And User clicks on "Submit" button
    And User should get error message "Please fill out this field."

























