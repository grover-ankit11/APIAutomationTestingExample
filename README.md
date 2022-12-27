# Assignment 2: API Automation Testing

## Setup
* This framework implementation uses:
  * **Java** as a coding language
  * **TestNg** as testing framework
  * **Maven** for Project building (https://maven.apache.org/install.html)
  * **JSON** parsing is used to read test data
  * **Jackson** is used for creating POJOs
  * **Lombok** is used to create Getter and Setter methods of the POJO

## Code Structure:
* Models and Utilities are in src\main\java\com\qa\application\models and src\main\java\com\qa\application\util package respectively
* Tests are in src\test\java\com\qa\application\testcases package
* Test data is in src\test\resources\testData package
* Extent report is generated inside reports folder with test Steps logs, Failed Screen shot and System info.
* Logs file is created inside logs folder

## Test Scenario Assumptions
* As there is no API to test this response, I'm using the API response as json file in testdata which I'm reading at runtime and creating POJOs accordingly
* The JSON file can be modified to test both positive and negative scenarios. The current version of the file in testData folder doesnot contain any invalid data which results in all tests being passed. The test report for the same can be accessed from Reports folder
* Voucher code must be a mix of number and character. If it contains only number or only characters it will fail

## TestCases Covered
* Partner Data fields Validation Test
  * Partner ID can't be null
  * Partner name can't be null or empty
  * Partner Image can't be null or empty
  * Partner Image URL must be valid URL
* Campaign Data fields Validation Test
  * Campaign ID can't be null
  * Campaign name can't be null or empty
  * Voucher Code can't be null or empty
  * Voucher code must contain both numbers and alphabets
* Partner ID Duplication Test
  * Each Partner should have a unique ID
* Campaign ID Duplication Test
  * Each Campaign should have a unique ID under a particular Partner ID

## How to run
* Run `mvn compile` on terminal to compile the code
* Run `mvn test` on terminal to run the tests