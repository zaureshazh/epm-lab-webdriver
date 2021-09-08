package page;

import business_objects.EmailContent;
import business_objects.TestEmailContent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reporting.LoggerUtil;
import reporting.ReportUtil;
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
        LoggerUtil.info("Checking if Mailbox is open");
        WaitUtil.waitForElementToBeVisible(composeButton, WAIT_TIMEOUT);
        return composeButton.isDisplayed();
    }

    public YandexMailBoxPF composeEmail() {
        WaitUtil.waitForElementToBeVisible(composeButton, WAIT_TIMEOUT);
        LoggerUtil.info("Composing new email");
        ReportUtil.highlightElement(composeButton, driver);
        composeButton.click();
        ReportUtil.unhighlightElement(composeButton, driver);
        WaitUtil.waitForElementToBeVisible(addresseeField, WAIT_TIMEOUT);
        LoggerUtil.info("Filling up email addressee");
        ReportUtil.highlightElement(addresseeField, driver);
        addresseeField.sendKeys(emailContent.getAddressee());
        ReportUtil.unhighlightElement(addresseeField, driver);
        LoggerUtil.info("Filling up email subject");
        ReportUtil.highlightElement(subjectField, driver);
        subjectField.sendKeys(emailContent.getSubject());
        ReportUtil.unhighlightElement(subjectField, driver);
        LoggerUtil.info("Filling up email body");
        ReportUtil.highlightElement(bodyField, driver);
        bodyField.sendKeys(emailContent.getBody());
        ReportUtil.unhighlightElement(bodyField, driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeEmailButton);
        return this;
    }

    public boolean checkDraft() {
        WaitUtil.waitForElementToBeVisible(draftsFolder, WAIT_TIMEOUT);
        LoggerUtil.info("Checking email is in Draft folder");
        ReportUtil.highlightElement(draftsFolder, driver);
        draftsFolder.click();
        ReportUtil.unhighlightElement(draftsFolder, driver);
        WaitUtil.waitForElementToBeVisible(firstDraft, WAIT_TIMEOUT);
        return addressee.getText().equals(emailContent.getAddressee()) && subject.getText().equals(emailContent.getSubject()) && body.getText().equals(emailContent.getBody());
    }

    public YandexMailBoxPF sendDraft() {
        WaitUtil.waitForElementToBeVisible(firstDraft, WAIT_TIMEOUT);
        LoggerUtil.info("Sending email");
        ReportUtil.highlightElement(firstDraft, driver);
        firstDraft.click();
        ReportUtil.unhighlightElement(firstDraft, driver);
        WaitUtil.waitForElementToBeVisible(sendEmailButton, WAIT_TIMEOUT);
        ReportUtil.highlightElement(sendEmailButton, driver);
        sendEmailButton.click();
        ReportUtil.unhighlightElement(sendEmailButton, driver);
        return this;
    }

    public boolean checkSent() {
        WaitUtil.waitForElementToBeVisible(sentFolder, WAIT_TIMEOUT);
        LoggerUtil.info("Checking email is sent");
        ReportUtil.highlightElement(sentFolder, driver);
        sentFolder.click();
        ReportUtil.unhighlightElement(sentFolder, driver);
        WaitUtil.waitForElementToBeVisible(addressee, WAIT_TIMEOUT);
        return addressee.getText().equals(emailContent.getAddressee()) && subject.getText().equals(emailContent.getSubject()) && body.getText().equals(emailContent.getBody());
    }

    public YandexMailBoxPF moveToTrash() {
        LoggerUtil.info("Refreshing the page");
        ((JavascriptExecutor) driver).executeScript("history.go(0)");
        WaitUtil.waitForElementToBeVisible(email, WAIT_TIMEOUT);
        WaitUtil.waitForElementToBeVisible(trashFolder, WAIT_TIMEOUT);
        LoggerUtil.info("Moving email Trash folder");
        new Actions(driver).dragAndDrop(email, trashFolder).build().perform();
        return this;
    }

    public void logout() {
        LoggerUtil.info("Logging out");
        WaitUtil.waitForElementToBeVisible(userMenu, WAIT_TIMEOUT);
        ReportUtil.highlightElement(userMenu, driver);
        userMenu.click();
        ReportUtil.unhighlightElement(userMenu, driver);
        WaitUtil.waitForElementToBeVisible(logoutOption, WAIT_TIMEOUT);
        ReportUtil.highlightElement(logoutOption, driver);
        logoutOption.click();
    }
}
