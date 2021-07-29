package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtil;

public class YandexLoginPagePF {
    private static final String HOMEPAGE_URL = "https://passport.yandex.com/";
    private static int WAIT_TIMEOUT = 10;
    private WebDriver driver;

    @FindBy(id = "passp-field-login")
    private WebElement loginField;

    @FindBy(id = "passp:sign-in")
    private WebElement loginButton;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    @FindBy(className = "personal-info__first")
    private WebElement name;

    @FindBy(className = "personal-info__last")
    private WebElement surname;

    @FindBy(className = "user-account__name")
    private WebElement accountMenu;

    @FindBy(className = "menu__text")
    private WebElement mailOption;

    public YandexLoginPagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YandexLoginPagePF openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public YandexLoginPagePF login() {
        WaitUtil.waitForElementToBeVisible(loginField, WAIT_TIMEOUT);
        loginField.sendKeys("jan3doetest@yandex.com");
        loginButton.click();
        WaitUtil.waitForElementToBeVisible(passwordField, WAIT_TIMEOUT);
        passwordField.sendKeys("testaccount123");
        loginButton.click();
        return new YandexLoginPagePF(driver);
    }

    public boolean checkIfLoggedIn() {
        WaitUtil.waitForElementToBeVisible(name, WAIT_TIMEOUT);
        WaitUtil.waitForElementToBeVisible(surname, WAIT_TIMEOUT);
        return name.isDisplayed() && surname.isDisplayed();
    }

    public YandexMailBoxPF openMailBox() {
        accountMenu.click();
        mailOption.click();
        return new YandexMailBoxPF(driver);
    }

}
