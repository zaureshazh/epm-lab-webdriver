package yandex;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(strict = true, plugin = {"json:target/cucumber-report.json", "html:target/cucumber-report"}, tags = "@yandexTest",
        features = "src/test/resources/cucumber_features/yandex_mail.feature", glue = {"src.test.steps"})

public class YandexMailPageCucumberTestNGTest extends AbstractTestNGCucumberTests {

}
