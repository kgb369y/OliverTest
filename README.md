# OliverTest
#Content
This project include the automation test cases defined and implemented to give coverage to Oliver Space web site to the purpose to evaluate to [Katy Garcia](https://www.linkedin.com/in/katygb) kgb369y@gmail.com to the position of Test Automation.

#Setup instructions
To execute this code is needed install JAVA latest version and MAVEN latest version and configure 
JAVA_HOME and MAVEN_HOME as ENV variables and include bin into the path.

#Execute instructions
Open a console into this project is download and use the following commands:

```
mvn clean install
```
to install the dependencies required compile the project and execute the the test. 


```
mvn test
```

if the project is already updated regarding dependencies and only want to run the tests implemented.

The pretty report in HTML format will be generated in the path 
*<projectSource>/target/cucumber-html-report/*