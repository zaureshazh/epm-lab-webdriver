package steps;

import config.DriverConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.YandexMailBoxPF;

public class YandexEmailSteps {
    private static WebDriver driver = DriverConfig.getChromeDriver();

    @Given("^user is on yandex mail page$")
    public void check_email_page_is_open() {
        Assert.assertTrue(new YandexMailBoxPF(driver).checkEmailPageIsOpen());
    }

    @When("^user performs \"([^\"]*)\" action on email$")
    public void perform_action_on_email(String action) {
        YandexMailBoxPF yandexMailBoxPF = new YandexMailBoxPF(driver);
        switch (action) {
            case "create" -> yandexMailBoxPF.composeEmail();
            case "send" -> {
                yandexMailBoxPF.checkDraft();
                yandexMailBoxPF.sendDraft();
            }
        }
    }

    @Then("^email is in \"([^\"]*)\"$")
    public void check_email_in_folder(String folder) {
        YandexMailBoxPF yandexMailBoxPF = new YandexMailBoxPF(driver);
        switch (folder) {
            case "draft" -> Assert.assertTrue(yandexMailBoxPF.checkDraft());
            case "sent" -> Assert.assertTrue(yandexMailBoxPF.checkSent());
        }
        yandexMailBoxPF.logout();
    }


}
