# ENTERPRISE_CUCUMBER_AUTOMATION_FRAMEWORK
This is an End to End  Robust Framework that helps teams to Automate any application.

Table of Contents:
Introduction
Framework Structure
The process of execution
Verifying the reports
Executing the project from Command line


1. INTRODUCTION:
This document contains information about the framework we used to complete the assessment and in detail explanation of frame work contents and execution details along with screen shots.
Framework setup/Configuration:
This framework has been Configured using the following components:
JDK 1.8
Selenium Web Driver Library (For automation)
Cucumber Library (To write BDD scenarios
JUnit (To execute the tests)
Maven (build tool to compile all the jars and Also to Download Dependencies)
Design Patterns Used:
1) Singleton Design Pattern for Single Instance of Excel and Database Connections
2) Factory Pattern for Instantiating the Specific Browsers at Runtime
3) Chain of Responsibility Design Pattern
4) Inversion of Control (IoC) / Dependency Injection to pass the Driver Instance in all pages 
5) Page Object Model 

Framework Structure:
The structure of this framework is made of various modules and each module has its own responsibility. Using this Framework we can Automate Both Web and Web Services(API). For Web Service Automation I have used HTTP Client. Apart  from this I have used SFTP Util to transfer files to a network  through Script without the help of WinScp or FileZilla. And also the Main Advantage using this Framework is we can parameterize the Data from Excel with one condition you just have to pass the Row Num and Column Name and Sheet Name in the Cucumber file.

Frame work Structure
![image](https://user-images.githubusercontent.com/35104378/222805515-14fe6d70-9ad4-4751-a05f-cf8e9eb5aa6b.png)

![image](https://user-images.githubusercontent.com/35104378/222805653-b63c6137-eb69-4361-b57b-b484b8f3e85d.png)

Resources (feature file):
Each feature file will contain business scenarios performed by actors. All the feature files will be stored in the folder src/test/resources/Features and I named my feature file as “GOOGLE_SEARCH.feature”  in this we are parameterizing data using feature Files and GOOGLE_SEARCH_WITH_EXCEL.feature  in which we parameterize the data using  Excel.

The process of execution:
Prerequisites:
Java should be installed and class path must be configured in the machine where we are executing this jar file.
Please disable firewalls which may stop the invocation of browser by selenium code in the machine
Firefox  version 40 and above to be installed in the machine
 Maven Should be Configured
In Maven if required you may have to give the proxy details user name and password if required in Settings.xml file.
Execution Steps:
Step1:
Download and copy the project into a local drive.
Step2:
Maven Clean and Install 
Step3:
In the project directory there is a batch file Runner.bat double click on that so execution will be started.
Step4:
Execution of scenarios will start and report will generate in “index.html” file.

Verifying the reports:
The final step is to verify the generated report. In this framework, all the reports will be resided in the target folder of framework and following are the steps to open it.
Step1:
Open the folder named “target” which will be there in the project directory.
Step2:
Navigate to the sub folder “cucumber-html-report” 
Step3:
Search for the file “index.html” inside the serenity folder and double click on it. You can see the generated reports.
Step4:
This report will contain step by with execution information. To go into more details click on the scenario link which will be there at the bottom of the report.
Step5:
Once you click on any one of the scenario link, it will display the step by step 

Executing the project from Command line :
Step1:
 Navigate to the Project path in command line where you have saved 
Step2:
Execute this command in Command line 
mvn -Dtest=Runner_File test  -Dcucumber.options="--tags @RunGoogle"
Execute this command in Command line for Executing the Scenarios which are parameterized in Excel
mvn -Dtest=Runner_File test  -Dcucumber.options="--tags @RunGooglewithExcel"

Changing Browsers :
Step1:
 Navigate to the Project Path and then navigate to CodingChallenge\src\test\resources\PropertyFiles\Appconstant.properties
Step2:
In AppConstant.properties there is a key Called Browser. So in that Key we can mention  specific browser name
  



