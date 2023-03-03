package runnertestfile;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;




@RunWith(Cucumber.class)
@CucumberOptions(
    features = { "classpath:features" }, // Location of your feature files
    glue =  {"com.stepdefs",}, // Location of your step definitions
    plugin = {"pretty", "html:target/cucumber-reports"}, // Report formats to use
    tags = "@RunGoogle"
)
public class Runner_File {
   
}

