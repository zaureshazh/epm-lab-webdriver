package yandex;

import business_objects.EmailContent;
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
    private EmailContent emailContent = new EmailContent();

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

    @Test(dependsOnMethods = "loginTest")
    public void createEmailTest() {
        yandexMailBoxPF = yandexLoginPagePF.openMailBox();
        yandexMailBoxPF.composeEmail(emailContent.getAddressee(), emailContent.getSubject(), emailContent.getBody());
    }

    @Test(dependsOnMethods = "createEmailTest")
    public void checkDraftsTest() {
        Assert.assertTrue(yandexMailBoxPF.checkDraft(emailContent.getAddressee(), emailContent.getSubject(), emailContent.getBody()));
        yandexMailBoxPF.sendDraft()
                .checkDraft(emailContent.getAddressee(), emailContent.getSubject(), emailContent.getBody());
    }

    @Test(dependsOnMethods = "checkDraftsTest")
    public void checkSentTest() {
        Assert.assertTrue(yandexMailBoxPF.checkSent(emailContent.getAddressee(), emailContent.getSubject(), emailContent.getBody()));
        yandexMailBoxPF.moveToTrash().logout();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
