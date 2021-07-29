package util;

import config.DriverConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    public static void waitForElementToBeVisible(WebElement webElement, int timeout) {
        new WebDriverWait(DriverConfig.getChromeDriver(), timeout)
                .until(ExpectedConditions.visibilityOf(webElement));
    }
}
