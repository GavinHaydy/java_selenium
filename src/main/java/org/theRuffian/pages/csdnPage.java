package org.theRuffian.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class csdnPage {
    public static By nickPic = By.cssSelector(".toolbar-btn.toolbar-btn-login.csdn-toolbar-fl.toolbar-subMenu-box");
//    public static By nickName = By.className("csdn-profile-nickName");
    @FindBy(className="csdn-profile-nickName")
    public static WebElement nickName;
    public static String url = "https://csdn.net";
}
