package org.theRuffian.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import lombok.SneakyThrows;

import org.openqa.selenium.support.locators.RelativeLocator.*;

public class BrowserOperation {
    WebDriver driver;
    public BrowserOperation(WebDriver driver){
        this.driver = driver;
    }

    public WebElement findElement( By locator) {
        if (locator instanceof RelativeBy) {
            return driver.findElement(locator);
        } else {
            return new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        }
    }

    public static List<WebElement> findElements(WebDriver driver, By locator) {
        if (locator instanceof RelativeBy) {
            return driver.findElements(locator);
        } else {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        }
    }

    public void get(String url){
        this.driver.get(url);
    }
    public void sendKeys(By by, String s) {
        findElement(by).sendKeys(s);
    }

    public void click(By by) {
        findElement(by).click();
    }

    public String getValue(By by, String name) {
        if ("text".equals(name)) {
            return findElement(by).getText();
        } else {
            return findElement(by).getAttribute(name);
        }
    }

    public String getValue(By by) {
        return getValue(by, "text");
    }

    public String getWindowValue(String val) {
        if ("title".equalsIgnoreCase(val)) {
            return this.driver.getTitle();
        } else if ("url".equalsIgnoreCase(val)) {
            return this.driver.getCurrentUrl();
        } else {
            return this.driver.getPageSource();
        }
    }

    @SneakyThrows
    public void sleep(long ms){
        Thread.sleep(ms);
    }
    public void setSize(Integer width, Integer height) {
        if (width == 1 && height == 1) {
            driver.manage().window().maximize();
        } else if (width == 0 && height == 0) {
            driver.manage().window().minimize();
        } else {
            driver.manage().window().setSize(new Dimension(width, height));
        }
    }

    public void setSize() {
        setSize(1, 1);
    }

    public RelativeBy locateWith(By by) {
        return RelativeLocator.with(by);
    }

    public RelativeBy locateWithAbove(By by, By ele) {
        return locateWith(by).above(ele);
    }

    public RelativeBy locateWithBelow(By by, By ele) {
        return locateWith(by).below(ele);
    }

    public RelativeBy locateWithLeft(By by, By ele) {
        return locateWith(by).toLeftOf(ele);
    }

    public RelativeBy locateWithRight(By by, By ele) {
        return locateWith(by).toRightOf(ele);
    }

    public RelativeBy locateWithNear(By by, By ele) {
        return locateWith(by).near(ele);
    }

    /**
     * Get Alerts | Confirm | Prompt
     * @return alerts
     */
    public Alert alerts() {

        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void frame(By by){
        driver.switchTo().frame(findElement(by));
    }

    public void frame(String name) {
        driver.switchTo().frame(name);
    }
    public void frame(int index){
        driver.switchTo().frame(index);
    }

    /**
     *
     * @param level 0|1
     */
    public void frameOut(int level){
        if (level == 1) {
            driver.switchTo().parentFrame();
        } else {
            driver.switchTo().defaultContent();
        }
    }

    /*
     * Mouse actions
     */

    public void moveToEle(By by){
        new Actions(driver)
                .moveToElement(findElement(by))
                .perform();
    }

    public void clickHold(By by){
        new Actions(driver)
                .clickAndHold(findElement(by))
                .perform();
    }

    public void clickReplace(By by){
        new Actions(driver)
                .click(findElement(by))
                .perform();
    }

    public void doubleClick(By by){
        new Actions(driver)
                .doubleClick(findElement(by))
                .perform();
    }
}
