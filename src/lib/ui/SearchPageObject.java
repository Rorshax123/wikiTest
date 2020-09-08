package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_CLOSE_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_ARTICLE_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_NO_RESULTS_FOUND_LABEL = "//*[@text='No results found']",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";

    /*TEMPLATE METHODS*/
    private static String getResultSubstring(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Can not find init search element and click it",  5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Can not find init search element after clicking in it",  5);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendkeys(By.xpath(SEARCH_INPUT), "Can not write given value to search field",  searchLine, 5);
    }

    public void waitForSearchResult(String nameOfArticle){
        String searchElementXpath = getResultSubstring(nameOfArticle);
        this.waitForElementPresent(By.xpath(searchElementXpath), "Cannot find " + nameOfArticle +" on search" , 5);
    }

    public void waitForSearchNotResult(){
        this.waitForElementNotPresent(By.xpath(SEARCH_ARTICLE_ELEMENT), "Founded some elements" , 5);
    }

    public void clickToCancelButton(){
        this.waitForElementPresent(By.id(SEARCH_CLOSE_BUTTON), "Can not find search button", 5);
    }

    public void waitForCancelButtonToClick(){
        this.waitForElementAndClick(By.id(SEARCH_CLOSE_BUTTON), "Can not click to search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CLOSE_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickByArticleWithSubString(String substring){
        String searchElementXpath = getResultSubstring(substring);
        this.waitForElementAndClick(By.xpath(searchElementXpath), "Cannot click " + substring +" on search" , 10);
    }

    public int getAmountOfSearchArticles(){
        this.waitForElementPresent(
                By.xpath(SEARCH_ARTICLE_ELEMENT),
                "Can not find any results",
                5
        );
        return this.getCountOfElements(By.xpath(SEARCH_ARTICLE_ELEMENT));
    }
    public void waitForNoResultsFoundButton(){
        waitForElementPresent(By.xpath(SEARCH_NO_RESULTS_FOUND_LABEL), "Can not found not results found button", 5);
    }

}
