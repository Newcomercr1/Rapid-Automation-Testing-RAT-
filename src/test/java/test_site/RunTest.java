package test_site;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = "src/test/resource/"
, //format={"pretty", "html:target/Site"},
format = { "pretty","json:target/cucumber.json" },
tags = { "~@ignore" })  

public class RunTest {
	
	
}
