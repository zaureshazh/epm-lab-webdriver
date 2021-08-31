package page;

import business_objects.EmailContent;
import business_objects.TestEmailContent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtil;

public class YandexMailBoxPF {
    private static int WAIT_TIMEOUT = 10;
    private WebDriver driver;
    private EmailContent emailContent = new TestEmailContent().buildEmailContent();

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

    @FindBy(xpath = "//div[@class='ComposeSendButton-Text']//ancestor::button")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//a[@data-title='Sent']")
    private WebElement sentFolder;

    @FindBy(className = "user-pic__image")
    private WebElement userMenu;

    @FindBy(xpath = "//span[contains(text(),'Log out')]")
    private WebElement logoutOption;

    @FindBy(xpath = "//div[@class='mail-MessageSnippet-Content']")
    private WebElement email;

    @FindBy(xpath = "//span[contains(text(), 'Trash')]")
    private WebElement trashFolder;

    public YandexMailBoxPF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkEmailPageIsOpen() {
        WaitUtil.waitForElementToBeVisible(composeButton, WAIT_TIMEOUT);
        return composeButton.isDisplayed();
    }

    public YandexMailBoxPF composeEmail() {
        WaitUtil.waitForElementToBeVisible(composeButton, WAIT_TIMEOUT);
        composeButton.click();
        WaitUtil.waitForElementToBeVisible(addresseeField, WAIT_TIMEOUT);
        addresseeField.sendKeys(emailContent.getAddressee());
        subjectField.sendKeys(emailContent.getSubject());
        bodyField.sendKeys(emailContent.getBody());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeEmailButton);
        return this;
    }

    public boolean checkDraft() {
        WaitUtil.waitForElementToBeVisible(draftsFolder, WAIT_TIMEOUT);
        draftsFolder.click();
        WaitUtil.waitForElementToBeVisible(firstDraft, WAIT_TIMEOUT);
        return addressee.getText().equals(emailContent.getAddressee()) && subject.getText().equals(emailContent.getSubject()) && body.getText().equals(emailContent.getBody());
    }

    public YandexMailBoxPF sendDraft() {
        WaitUtil.waitForElementToBeVisible(firstDraft, WAIT_TIMEOUT);
        firstDraft.click();
        WaitUtil.waitForElementToBeVisible(sendEmailButton, WAIT_TIMEOUT);
        sendEmailButton.click();
        return this;
    }

    public boolean checkSent() {
        WaitUtil.waitForElementToBeVisible(sentFolder, WAIT_TIMEOUT);
        sentFolder.click();
        WaitUtil.waitForElementToBeVisible(addressee, WAIT_TIMEOUT);
        return addressee.getText().equals(emailContent.getAddressee()) && subject.getText().equals(emailContent.getSubject()) && body.getText().equals(emailContent.getBody());
    }

    public YandexMailBoxPF moveToTrash() {
        ((JavascriptExecutor)driver).executeScript("history.go(0)");
        WaitUtil.waitForElementToBeVisible(email, WAIT_TIMEOUT);
        WaitUtil.waitForElementToBeVisible(trashFolder, WAIT_TIMEOUT);
        new Actions(driver).dragAndDrop(email, trashFolder).build().perform();
        return this;
    }

    public void logout() {
        WaitUtil.waitForElementToBeVisible(userMenu, WAIT_TIMEOUT);
        userMenu.click();
        WaitUtil.waitForElementToBeVisible(logoutOption, WAIT_TIMEOUT);
        logoutOption.click();
    }
}
