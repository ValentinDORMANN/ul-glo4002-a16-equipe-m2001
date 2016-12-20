package ca.ulaval.glo4002.flycheckin.flycheckin_uat;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" }, features = "classpath:flycheckin_uat/")
public class AcceptanceTest {

}
