package org.example.testCase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.theRuffian.common.OpenBrowser;
import org.theRuffian.common.BrowserOperation;
import org.theRuffian.pages.csdnPage;

public class CSDNTest {
    private final SoftAssert softAssert = new SoftAssert();
    WebDriver driver;
    BrowserOperation dri;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
//        driver = OpenBrowser.openBrowser("chrome", "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data");
        driver = OpenBrowser.openBrowser("firefox", "C:\\Users\\Administrator\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\sgygoxs2.default-release");
        dri = new BrowserOperation(driver);
        dri.get(csdnPage.url);
    }
    @AfterMethod(alwaysRun = true)
    public void quitBrowser(){
        System.out.println("关闭");
        driver.quit();
    }

    @Test
    public void userName(){
        try {
//            Thread.sleep(2000);
            dri.moveToEle(csdnPage.nickPic);
            Thread.sleep(3000);
            String nickName = dri.getValue(csdnPage.nickName,"text");
            softAssert.assertEquals(nickName, "the-ruffian");
            softAssert.assertAll();
        } catch (Exception e) {
            System.out.println(111);
        }


    }
}
