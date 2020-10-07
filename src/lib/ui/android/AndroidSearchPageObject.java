package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

     static {
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
            SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
            SEARCH_CLOSE_BUTTON = "id:org.wikipedia:id/search_close_btn";
            SEARCH_ARTICLE_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
            SEARCH_NO_RESULTS_FOUND_LABEL = "xpath://*[@text='No results found']";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
            SEARCH_RESULT_INDEX_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container'][@index='{INDEX}']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
            SEARCH_RESULT_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']";
            SEARCH_RESULT_DESC_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_description'][@text='{DESC}']";
     }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
