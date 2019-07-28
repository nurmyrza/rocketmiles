![rocketmiles_logo](https://www.rocketmiles.com/resources/images/logos/rm3-logo-dark@2x.png)
##
# Rocketmiles
## 

This is the simplest build script setup for Search functionality Java.
All this does is to show you scenarios of testing Search functionality based on [rocketmiles.com](https://www.rocketmiles.com/) Website.

There is a single feature file with a few scenarios. There are two positive scenarios. 
First scenario has couple steps, where we basically checking possibility order hotel and see if you can make them all pass!
Second scenario has multiple runs with using price range and validation process goes through few input data to see if you can make them all pass!
Another three scenarios checking negative process where we checking limit(prohibit) process of order steps to see if you can make them all pass and proof limit working!

## Get the code

Git:

    git clone https://github.com/nurmyrza/rocketmiles.git
    cd rocketmiles

Or simply [download a zip](https://github.com/nurmyrza/rocketmiles/archive/master.zip) file.

## Use Maven

Prerequisites(tested):

* Java 1.8
* Maven > 3.6.1

Compile with Maven:
run from project root: `mvn clean compile`

Recommendation: If using IntelliJ and after cloning will not able to determine as a Maven project,
find pom.xml file and with right click Mark as a Maven project, after that will automatically determine
as a Maven project and dependencies will be downloaded. 

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `Runner`
class tells JUnit to kick off Cucumber.

## Use IDE or CLI

There are two way to run program
1. `IDE`
2. `CLI`

## Running through IDE
Navigate to Runner class(`src\test\java\runner\Runner.java`) and run this class, it will execute and based on Cucumber 
feature file `src\test\resources\testFeatures\search.feature` will execute first negative scenarios (`tags = {"@positive, @negative"},`--> `see.Runner class`)
You can find result ...

## Running through CLI

Open a command window and run:

    mvn test

## Maven project structure
**Directory:**
~~~
src/
|test/
    |java/
         |pages/                    
            |RocketmilesPage       
            |SearchResultPage
         |runner/  
            |Runner  
         |stepDefinitions                 
            |Hooks
            |SearchStepDef
         |utilities/
            |Configuration
            |Driver
    |resourses
        |testFeatures/
            |search.feature
        |log4j2.properties
    |configuration.properties
    |pom.xml
    |README.md
~~~

## Browser compatible

You ca run use two browser to run program:

... `Chrome`

To use that feature navigate to `..\configuration.properties` file and check `browser=1` parameter. It should be `browser=1`.
By taking parameter 1, it will run on Chrome Browser. Base URL is: https://www.rocketmiles.com/ 


... `FireFox`

To use that feature navigate to `..\configuration.properties` file and change `browser=2` parameter. It should be `browser=2`.
By taking parameter 2, it will run on FireFox Browser. Base URL is: https://www.rocketmiles.com/ 


## Report

After running you will find report under `Target` folder


>Output(IDE):

7 Scenarios (7 passed)

25 Steps (25 passed)

2m39.130s




