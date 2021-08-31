package steps;

import config.DriverConfig;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import page.YandexLoginPagePF;

public class YandexLoginSteps {
    private static WebDriver driver = DriverConfig.getChromeDriver();

    @Given("^user (?:navigates to|opens) yandex login page$")
    public void navigate_to_home_page() {
        new YandexLoginPagePF(driver).openPage();
    }

    @When("^enters user credentials and logs in$")
    public void enter_user_credentials() {
        new YandexLoginPagePF(driver).login();
    }

    @Then("^yandex home page is displayed$")
    public void verify_login_is_completes() {
        Assert.assertTrue(new YandexLoginPagePF(driver).checkIfLoggedIn());
    }

    @And("^user (?:navigates to|opens) yandex mail page$")
    public void navigate_to_mail_page() {
        new YandexLoginPagePF(driver).openMailBox();
    }
}
