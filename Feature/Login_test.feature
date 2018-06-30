Feature: login and logout

Scenario: Successful Login with Valid Credentials

	Given User is on EmailTypeList Page
	When User Navigate to 126 Mail Login Page
	And User enters UserName and Password
	Then Message displayed Login Successfully

Scenario:
	
	When User LogOut from the Application
	Then Message display LogOut Successfully