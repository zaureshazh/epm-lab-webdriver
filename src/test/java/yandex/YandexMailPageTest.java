package yandex;

import config.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.YandexLoginPagePF;
import page.YandexMailBoxPF;

public class YandexMailPageTest {
    private WebDriver driver;
    private YandexLoginPagePF yandexLoginPagePF;
    private YandexMailBoxPF yandexMailBoxPF;

    @BeforeMethod(alwaysRun = true)
    public void setupBrowser() {
        driver = DriverConfig.getChromeDriver();
        yandexLoginPagePF = new YandexLoginPagePF(driver);
    }

    @Test
    public void loginTest() {
        yandexLoginPagePF
                .openPage()
                .login();
        Assert.assertTrue(yandexLoginPagePF.checkIfLoggedIn());
    }

    @Test
    public void createEmailTest() {
        loginTest();
        yandexMailBoxPF = yandexLoginPagePF.openMailBox();
        yandexMailBoxPF.composeEmail();
    }

    @Test
    public void checkDraftsTest() {
        createEmailTest();
        Assert.assertTrue(yandexMailBoxPF.checkDraft());
        yandexMailBoxPF.sendDraft()
                .checkDraft();
    }

    @Test
    public void checkSentTest() {
        checkDraftsTest();
        Assert.assertTrue(yandexMailBoxPF.checkSent());
        yandexMailBoxPF.moveToTrash().logout();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
