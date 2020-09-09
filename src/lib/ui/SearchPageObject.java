package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.omg.CORBA.PUBLIC_MEMBER;
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
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_INDEX_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container'][@index='{INDEX}']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
        SEARCH_RESULT_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']",
        SEARCH_RESULT_DESC_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_description'][@text='{DESC}']";

    /*TEMPLATE METHODS*/

    private static String getResultSubstring(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getElementIndex(String index){
        return SEARCH_RESULT_INDEX_TPL.replace("{INDEX}", index);
    }

    private static String getElementTitle(String title){
        return SEARCH_RESULT_TITLE_TPL.replace("{TITLE}", title);
    }

    private static String getElementDesc(String desc){
        return SEARCH_RESULT_DESC_TPL.replace("{DESC}", desc);
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

    public void waitForXButtonToClick(){
        this.waitForElementAndClick(By.id(SEARCH_CLOSE_BUTTON), "Can not click to search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CLOSE_BUTTON), "Search cancel button is still present", 5);
    }

    public void waitForNoResultsFoundButton(){
        waitForElementPresent(By.xpath(SEARCH_NO_RESULTS_FOUND_LABEL), "Can not found not results found button", 5);
    }
    public void waitElementOfSearchPresent(String text, String indexStartsWithZero){
        this.waitForSearchElementPresentByText(
                By.xpath(getElementIndex(indexStartsWithZero)),
                text,
                "Cannot find text "+text+"",
                5
        );
    }

    public void waitElementOfSearchNotPresent(String indexStartsWithZero){
        this.waitForElementNotPresent(
                By.xpath(getElementIndex(indexStartsWithZero)),
                "Some elements founded by search",
                5
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        String elementTitle = getElementTitle(title);
        String elementDesc = getElementDesc(description);
        this.waitForElementPresent(By.xpath(elementTitle), "Cannot find " + title +" on search" , 10);
        this.waitForElementPresent(By.xpath(elementDesc), "Cannot find " + description +" on search" , 10);
    }

    public void clickByArticleWithSubString(String substring){
        String searchElementXpath = getResultSubstring(substring);
        this.waitForElementAndClick(By.xpath(searchElementXpath), "Cannot click " + substring +" on search" , 10);
    }

    public void clickToXBtn(){
        this.waitForElementAndClick(By.id(SEARCH_CLOSE_BUTTON), "Can not find search button", 5);
    }

    public int getAmountOfSearchArticles(){
        this.waitForElementPresent(
                By.xpath(SEARCH_ARTICLE_ELEMENT),
                "Can not find any results",
                5
        );
        return this.getCountOfElements(By.xpath(SEARCH_ARTICLE_ELEMENT));
    }

}
