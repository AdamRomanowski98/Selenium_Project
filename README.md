#Introduction
Automating functional UI and end to end tests for https://demowebshop.tricentis.com/. Repository contains
Selenium WebDriver and Junit5 tests on Chrome and Edge browser. All tests will run in headless mode
by default.If any of the tests fails due to website instability simply rerun it.

#Stack & Libraries
- Java
- Selenium WebDriver
- Junit 5
- Maven
- Allure 

#Prerequisites
- IDE
- Maven
- JDK 11+ 


#How to run:
1. git clone https://Deloitte-CE-IT@dev.azure.com/Deloitte-CE-IT/CE_SI_LAB_group_C/_git/CE_SI_LAB_group_C to your local folder
2. Open cloned directory in IDE
3. Open terminal
4. Type mvn clean test to run the tests
5. Type mvn clean test allure:report allure:serve to run the tests along with allure report

#Change configuration
Go to src/test/java/com/deloitte/hackaton/TestsSetup
Change String webDriver value to
- "chrome" - to run in Chrome browser with GUI
- "chrome-headless" - to run in Chrome browser in headless mode
- "edge" - to run in Microsoft Edge browser with GUI
- "edge-headless" - to run in Edge browser in headless mode


#Project components
- Page object models are located in the directory src/main/java/com/deloitte/hackaton/page
- Test classes are located in the directory src/test/java/com/deloitte/hackaton
- .json files are located in the directory src/main/resources/static


#Highlights
- This framework supports both Chrome and Edge browser.
- Project uses WebDriverManager, an open source browser binary manager.
- Screenshot on test failure: A screenshot of the active browser is captured and stored in the screenshots folder.
- Allure reporting: After the test finishes, a visual report is generated for all the executed test cases from the suite


