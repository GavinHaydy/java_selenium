package org.example.testCase;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.theRuffian.common.base;
import org.theRuffian.common.browserOperation;
import org.theRuffian.pages.homePage;

import java.time.Duration;


public class SearchTest {
    private final SoftAssert softAssert = new SoftAssert();
    WebDriver driver;
    browserOperation dri;
    @BeforeMethod(alwaysRun = true)
    public void openBrowser(){
        driver = base.openBrowser("firefox", "C:\\Users\\Administrator\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\sgygoxs2.default-release");
        dri = new browserOperation(driver);
        dri.get(homePage.url);

    }

    @Test
    public void search(){
        dri.sendKeys(homePage.searchText,"selenium");
        dri.click(homePage.searchClick);
        System.out.println("s1" + Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
            System.out.println( "===========================" +driver.getTitle());
        }catch (Exception e){
            System.out.println(1);
        }
        System.out.println( "===========================" +driver.getTitle());
        softAssert.assertEquals(driver.getTitle(),"selenium_百度搜索");
        softAssert.assertAll();
    }

    @Test
    public void search0(){
        dri.sendKeys(homePage.searchText,"测试哟");
        dri.click(homePage.searchClick);
        System.out.println("s2" + Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
            System.out.println( "===========================" +driver.getTitle());
        }catch (Exception e){
            System.out.println(1);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        softAssert.assertEquals(driver.getTitle(),"测试哟_百度搜索");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser(){
        System.out.println("关闭");
        driver.quit();
    }

}
