package com.youxinger.springbootcucumbergradle;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

//TEST FOR: testCustomerRecharge.feature

@RunWith(Cucumber.class)
@SpringBootTest(classes = {SpringbootcucumbergradleApplication.class})
@CucumberOptions(plugin = {"json:target/cucumber.json", "pretty"}, features = "src/test/resources", tags = {"@testUnPaidOrderCancel"}, format = {
        "html:target/html-report/",
        "json:target/json-report/dw.json"
})
public class CustomerRechargeTest {
    static {
        RestAssured.registerParser("text/plain", Parser.JSON);
    }
}