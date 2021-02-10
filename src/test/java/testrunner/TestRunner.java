package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\v-raango\\workspace\\E2Eapi\\src\\test\\java\\features",glue="stepdefinitions")
public class TestRunner {
	
	

}
