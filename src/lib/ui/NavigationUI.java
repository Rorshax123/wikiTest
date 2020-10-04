package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    private static final String
        MY_LISTS_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";


    public void clickToMyListsButton(){
        this.waitForElementAndClick(
                MY_LISTS_BUTTON,
                "Cannot find My lists button",
                5
        );
    }
}
