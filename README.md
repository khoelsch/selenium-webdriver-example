# Selenium WebDriver Example

Project demonstrating the use of WebDriver for automated browser tests, using Selenium 2 as implementation.


## Prerequisites

* Java JDK 8 installed 
* Maven 3.5.3+ installed

## Getting Started 

0. Start system-under-test (SUT) from Docker image
0. Obtain IP and port
0. <CONFIGURATION_OF_IP_AND_PORT> (currently manually edit StartPageTest.java)
0. Navigate to this project's root dir
0. Open terminal and call 

````
  mvn clean verify -Pexecute-ui-tests
````

## Checking for New Dependency Versions

Print new available versions to console with...

````
  mvn versions:display-dependency-updates
````

## Used Components

* [AssertJ](http://joel-costigliola.github.io/assertj/) for implementing checks
* [Junit 5](https://junit.org/junit5/docs/current/user-guide/) for test execution
* [Selenium Jupiter](https://bonigarcia.github.io/selenium-jupiter/) for staring Selenium from Maven artifact (instead of manually installed binary)
* [WebDriver API](https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/WebDriver.html) via [Selenium 2](https://www.seleniumhq.org/docs/03_webdriver.jsp#chapter03-reference)