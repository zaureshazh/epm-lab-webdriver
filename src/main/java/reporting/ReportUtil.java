package reporting;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ReportUtil {

    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    public static void highlightElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid green'", element);
    }

    public static void unhighlightElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }

    public static void takeScreenshot(String data, WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + data + ".png");
            FileUtils.copyFile(screenshot, copy);
            LoggerUtil.info("Saved screenshot: " + screenshotName);
        } catch (IOException e) {
            LoggerUtil.error("Failed to make screenshot");
        }
    }
}
