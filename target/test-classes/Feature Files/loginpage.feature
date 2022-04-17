Feature: To login telugu status videos

Scenario Outline: To validate the login with validate username and password

Given User has given chrome browser and telugustatus.com url

When User has to pass values to username and password fields

And then click on the login button

Then user has to nagivate to home page

Examples:
|username|password|
|priya|Kiran@123|


