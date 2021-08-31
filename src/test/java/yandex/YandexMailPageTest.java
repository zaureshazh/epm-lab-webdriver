package yandex;

import org.testng.Assert;
import org.testng.annotations.*;
import page.YandexLoginPagePF;
import page.YandexMailBoxPF;

public class YandexMailPageTest extends BaseYandexTest{
    private YandexLoginPagePF yandexLoginPagePF;
    private YandexMailBoxPF yandexMailBoxPF;

    @BeforeClass
    public void login() {
        yandexLoginPagePF = new YandexLoginPagePF(super.setupBrowser());
        yandexLoginPagePF.openPage().login();
    }

    @Test
    public void createEmailTest() {
        yandexMailBoxPF = yandexLoginPagePF.openMailBox();
        yandexMailBoxPF.composeEmail();
        Assert.assertTrue(yandexMailBoxPF.checkDraft());
    }

    @Test(dependsOnMethods = "createEmailTest")
    public void checkSentTest() {
        yandexMailBoxPF.sendDraft()
            .checkDraft();
        Assert.assertTrue(yandexMailBoxPF.checkSent());
        yandexMailBoxPF.moveToTrash().logout();
    }
}
