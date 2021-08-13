package yandex;

import config.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseYandexTest {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public WebDriver setupBrowser() {
        return driver = DriverConfig.getChromeDriver();
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
