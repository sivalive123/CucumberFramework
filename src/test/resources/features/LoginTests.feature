#Author Rupa.nepalr@aetna.com
#Keywords Summary This will test login functnality of FamilyConnect
Feature: US720842 FamilyConnect- SignIn/LogIn Page - Feature Forward- Testing.
Scenario: Aetna Better Health of Ohio is seen on the Sign In Page for Family Connect
Given Member user has entered the URL for FamilyConnect
When user has navigated to the Sign in Page
Then user sees the ABH of Ohio logo found here  this folder in the Repository.
And logo navigates to  https//www.aetnabetterhealth.com/ohiorise/index.html
And Find a provider https//www.aetnabetterhealth.com/ohiorise/find-provider
And Do not include about us or contact us in the header.

Scenario: Member & Non Member are able to login FamilyConnect in bold
Given Member user has entered the URL for FamilyConnect
When user has navigated to the Sign in Page
Then user sees the Verbiage as Our total approach to health and wellness takes care of your body, mind and spirit.  Get the most out of your benefits and services to support your path to better health
And Log in as Member
And Log in as Non-Member

Scenario:  Splash Image is visible
Given Member user has entered the URL for FamilyConnect
When user has navigated to the Sign in Page
Then Validate that Splash Image is visible

Scenario:  User sees a Need to register section where Members and Providers can self register
Given  when Member is on the sign in page
When Member Clicks on Register as Member 
Then member user is able to navigate to registration page
And  when Provider is on the sign in page
When Provider Clicks on Register as Provider
Then Provider user is able to navigate to registration page

Scenario: Footer of page includes Privacy Policy, Legal Statement and Non Discrimination Notice
Given Member user has entered the URL for FamilyConnect
When user has navigated to the Sign in Page
And Privacy Policy https//www.aetnabetterhealth.com/ohiorise/footers/privacy.html 
And Legal Statement https//www.aetnabetterhealth.com/ohiorise/footers/legal-statement.html 
And Non-Discrimination Notice https//www.aetnabetterhealth.com/ohiorise/notice-of-non-discrimination.html