package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{

    private static final String
        TITLE_THE_FREE_ENCYCLOPEDIA = "id:The free encyclopedia",
        TITLE_THE_WAYS_TO_EXPLORE = "id:New ways to explore",
        TITLE_SEARCH_IN_300 = "id:Search in nearly 300 languages",
        TITLE_HELP_MAKE_APP_BETTER = "id:Help make the app better",
        NEXT_BTN = "id:Next",
        GET_STARTED_BTN = "id:Get started";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitTitleFreeEnc(){
        this.waitForElementPresent(TITLE_THE_FREE_ENCYCLOPEDIA, "Can not find 'free encyclopedia' title", 10);
    }
    public void waitTitleWaysToExplore(){
        this.waitForElementPresent(TITLE_THE_WAYS_TO_EXPLORE, "Can not find 'ways to explore' title", 5);
    }
    public void waitTitleSearchIn300(){
        this.waitForElementPresent(TITLE_SEARCH_IN_300, "Can not find 'title search in 300' title", 5);
    }
    public void waitTitleHelpMakeAppBetter(){
        this.waitForElementPresent(TITLE_HELP_MAKE_APP_BETTER, "Can not find 'help make app better' title", 5);
    }
    public void clickNext(){
        this.waitForElementAndClick(NEXT_BTN, "Can not find 'next' btn", 5);
    }
    public void clickGetStarted(){
        this.waitForElementAndClick(GET_STARTED_BTN, "Can not find 'get started' btn", 5);
    }

}
