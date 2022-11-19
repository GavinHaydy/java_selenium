package org.theRuffian.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class base {
    static WebDriver driver;
    public static WebDriver openBrowser(String driverName) {
        if (driverName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (driverName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (driverName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (driverName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            driver = openBrowser("chrome");
        }
        return driver;
    }
    public static WebDriver openBrowser(String driver, String configPath, boolean Headless) {
        WebDriver result;
        if (driver.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-data-dir=" + configPath);
            if (Headless){
                options.addArguments("-headless");
            }
            result = new ChromeDriver(options);
        } else if (driver.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-profile=" + configPath);
            if (Headless){
                firefoxOptions.addArguments("-headless");
            }
            result = new FirefoxDriver(firefoxOptions);
        } else {
            result = openBrowser("chrome", configPath);
        }
        return result;
    }

    public static WebDriver openBrowser(String driver, String configPath){
        return openBrowser(driver,configPath,false);
    }
}
