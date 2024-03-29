package org.example.testCase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.theRuffian.common.base;
import org.theRuffian.common.browserOperation;
import org.theRuffian.pages.csdnPage;

public class CSDNTest {
    private final SoftAssert softAssert = new SoftAssert();
    WebDriver driver;
    browserOperation dri;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        driver = base.openBrowser("chrome", "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data");
//        driver = base.openBrowser("firefox", "C:\\Users\\Administrator\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\sgygoxs2.default-release");
        dri = new browserOperation(driver);
        dri.get(csdnPage.url);
    }
    @AfterMethod(alwaysRun = true)
    public void quitBrowser(){
        System.out.println("关闭");
        driver.quit();
    }

    @Test
    public void userName(){
        dri.moveToEle(csdnPage.nickPic);
        dri.sleep(5000);
        String nickName = dri.getValue(csdnPage.nickName);
        softAssert.assertEquals(nickName,"the-ruffian123");
        softAssert.assertAll();
    }
}
