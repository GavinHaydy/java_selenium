package org.example.testCase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.theRuffian.common.base;
import org.theRuffian.common.browserOperation;
import org.theRuffian.pages.homePage;

import java.io.IOException;
import java.time.Duration;

import static org.theRuffian.common.documentOperation.readCsv;

public class SearchXTest {
    private final SoftAssert softAssert = new SoftAssert();
    WebDriver driver;
    browserOperation dri;

    @DataProvider(name = "data01")
    public static Object[][] getData(){
//        return new Object[][]{{"测试", "测试_百度搜索"},{"java","java_百度搜索"},{"linux","linux_百度搜索"},{"Go","Go_百度搜索"}};
        try {
            return readCsv("D:\\data\\wwwroot\\WebAutomation\\src\\main\\java\\org\\theRuffian\\data\\test.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void openBrowser(){
        driver = base.openBrowser("fireFox");
        dri = new browserOperation(driver);
        dri.get(homePage.url);

    }

    @Test(dataProvider = "data01")
    public void search(String a, String b){
        dri.sendKeys(homePage.searchText,a);
        dri.click(homePage.searchClick);
        System.out.println("s1" + Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
            System.out.println( "===========================" +driver.getTitle());
        }catch (Exception e){
            System.out.println(1);
        }
        System.out.println( "===========================" +driver.getTitle());
        softAssert.assertEquals(driver.getTitle(),b);
        softAssert.assertAll();
    }

    @Test
    public void search0(){
        dri.sendKeys(homePage.searchText,"嘿嘿");
        dri.click(homePage.searchClick);
        System.out.println("s2" + Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
            System.out.println( "===========================" +driver.getTitle());
        }catch (Exception e){
            System.out.println(1);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        softAssert.assertEquals(driver.getTitle(),"嘿嘿_百度搜索");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser(){
        System.out.println("关闭");
        driver.quit();
    }
}
