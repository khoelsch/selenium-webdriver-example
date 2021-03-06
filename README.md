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



## Used Components

* [AssertJ](http://joel-costigliola.github.io/assertj/) for implementing checks
* [Junit 5](https://junit.org/junit5/docs/current/user-guide/) for test execution
* [Selenium Jupiter](https://bonigarcia.github.io/selenium-jupiter/) for staring Selenium from Maven artifact (instead of manually installed binary)
* [WebDriver API](https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/WebDriver.html) via [Selenium 2](https://www.seleniumhq.org/docs/03_webdriver.jsp#chapter03-reference)



## Maven Goodies


### Checking for New Dependency Versions

Print new available dependency versions with...

````
  mvn versions:display-dependency-updates
````

...and new available _plugin_ versions with...

````
  mvn versions:display-plugin-updates
````

### Third-party Licenses

In order to generate module-wise license information, activate profile ``generate-licenses`` as such...

````
  mvn clean package -Pgenerate-licenses
````

License information of used Maven dependencies is generated _in each module_ in two forms:

0. An XML file containing the names of all used licenses in ``<MODULE_ROOT>/target/generated-resources/licenses.xml``
0. Each license in either HTML or plain-text in ``<MODULE_ROOT>/target/generated-resources/licenses``


You can generate an aggregated license file on demand with...

````
  mvn license:aggregate-add-third-party
````

The aggregated file is generated in ``<PROJECT_ROOT>\target\generated-sources\license\THIRD-PARTY.txt`` (on windows).

More on how to merge licenses with similar, but not identical name can be found in [the plugin's documentation](http://www.mojohaus.org/license-maven-plugin/examples/example-thirdparty.html).