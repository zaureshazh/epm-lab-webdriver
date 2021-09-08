package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

//        try {
//            driver = new RemoteWebDriver(new URL("http://192.168.0.12:8090/wd/hub"), DesiredCapabilities.chrome());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        driver.manage().window().maximize();
        return driver;
    }
}
