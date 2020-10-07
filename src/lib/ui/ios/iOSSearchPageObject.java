package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {
                SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
                SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[contains(@type, 'XCUIElementTypeSearchField')]";
                SEARCH_CLOSE_BUTTON = "id:Close";
                SEARCH_NO_RESULTS_FOUND_LABEL = "id:No results found";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
                SEARCH_RESULT_TITLE_TPL = "id:{TITLE}";
                SEARCH_RESULT_DESC_TPL = "id:{DESC}";


    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
