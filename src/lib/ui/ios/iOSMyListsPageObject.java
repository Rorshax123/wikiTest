package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
                ARTICLE_NAME_ON_RL = "xpath://*[@text='Java (programming language)']";
                MY_LIST_LINK_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']";
                ARTICLE_NAME_ON_RL_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
    }
    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
