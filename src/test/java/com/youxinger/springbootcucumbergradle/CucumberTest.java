package com.youxinger.springbootcucumbergradle;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@SpringBootTest(classes = {SpringbootcucumbergradleApplication.class})
@CucumberOptions(plugin = {"json:target/cucumber.json", "pretty"}, features = "src/test/resources", tags = {"~@SmokeTest~@lanchao"}, format = {
        "html:target/html-report/",
        "json:target/json-report/dw.json"
})
@ContextConfiguration // 不加此注解，bean会注入不进去
public class CucumberTest {
}
