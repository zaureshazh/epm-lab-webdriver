package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverConfig {
    private static WebDriver driver;

    private DriverConfig() {

    }

    public static WebDriver getChromeDriver() {
        if (driver == null) {
            driver = init();
        }
        return driver;
    }

    private static WebDriver init() {
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.0.12:8090/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.manage().window().maximize();
        }
        return driver;
    }
}
