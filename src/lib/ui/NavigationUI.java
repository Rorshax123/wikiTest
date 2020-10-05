package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    protected static String
        MY_LISTS_BUTTON;


    public void clickToMyListsButton(){
        this.waitForElementAndClick(
                MY_LISTS_BUTTON,
                "Cannot find My lists button",
                5
        );
    }
}
