package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtil;

public class YandexMailBoxPF {
    private static int WAIT_TIMEOUT = 10;
    private WebDriver driver;

    @FindBy(xpath = "//a[@href='#compose']")
    private WebElement composeButton;

    @FindBy(className = "composeYabbles")
    private WebElement addresseeField;

    @FindBy(xpath = "//input[@class='composeTextField ComposeSubject-TextField']")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement bodyField;

    @FindBy(xpath = "//div[@class='ControlButton ControlButton_button_close']")
    private WebElement closeEmailButton;

    @FindBy(xpath = "//a[@data-title='Drafts']")
    private WebElement draftsFolder;

    @FindBy(xpath = "//div[@count='1']")
    private WebElement firstDraft;

    @FindBy(xpath = "//span[@title='jan3doetest@yandex.com']")
    private WebElement addressee;

    @FindBy(xpath = "//span[@title='test']")
    private WebElement subject;

    @FindBy(xpath = "//span[@title='testtest']")
    private WebElement body;

    @FindBy(xpath = "//button[@class='control button2 button2_view_default button2_tone_default button2_size_l button2_theme_normal button2_pin_circle-circle ComposeControlPanelButton-Button ComposeControlPanelButton-Button_sidePadding']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//a[@data-title='Sent']")
    private WebElement sentFolder;

    @FindBy(className = "user-pic__image")
    private WebElement userMenu;

    @FindBy(xpath = "//span[contains(text(),'Log out')]")
    private WebElement logoutOption;

    public YandexMailBoxPF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YandexMailBoxPF composeEmail(String addressee, String subject, String body) {
        WaitUtil.waitForElementToBeVisible(composeButton, WAIT_TIMEOUT);
        composeButton.click();
        WaitUtil.waitForElementToBeVisible(addresseeField, WAIT_TIMEOUT);
        addresseeField.sendKeys(addressee);
        subjectField.sendKeys(subject);
        bodyField.sendKeys(body);
        closeEmailButton.click();
        return this;
    }

    public boolean checkDraft(String addresseeTest, String subjectTest, String bodyTest) {
        WaitUtil.waitForElementToBeVisible(draftsFolder, WAIT_TIMEOUT);
        draftsFolder.click();
        WaitUtil.waitForElementToBeVisible(firstDraft, WAIT_TIMEOUT);
        return addressee.getText().equals(addresseeTest) && subject.getText().equals(subjectTest) && body.getText().equals(bodyTest);
    }

    public YandexMailBoxPF sendDraft() {
        firstDraft.click();
        WaitUtil.waitForElementToBeVisible(sendEmailButton, WAIT_TIMEOUT);
        sendEmailButton.click();
        return this;
    }

    public boolean checkSent(String addresseeTest, String subjectTest, String bodyTest) {
        WaitUtil.waitForElementToBeVisible(sentFolder, WAIT_TIMEOUT);
        sentFolder.click();
        WaitUtil.waitForElementToBeVisible(addressee, WAIT_TIMEOUT);
        return addressee.getText().equals(addresseeTest) && subject.getText().equals(subjectTest) && body.getText().equals(bodyTest);
    }

    public void logout() {
        WaitUtil.waitForElementToBeVisible(userMenu, WAIT_TIMEOUT);
        userMenu.click();
        WaitUtil.waitForElementToBeVisible(logoutOption, WAIT_TIMEOUT);
        logoutOption.click();
    }
}
