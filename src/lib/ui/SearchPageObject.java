package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class SearchPageObject extends MainPageObject{

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CLOSE_BUTTON,
        SEARCH_ARTICLE_ELEMENT,
        SEARCH_NO_RESULTS_FOUND_LABEL,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_INDEX_TPL,
        SEARCH_RESULT_TITLE_TPL,
        SEARCH_RESULT_DESC_TPL;

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
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can not find init search element and click it",  5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Can not find init search element after clicking in it",  5);
    }

    public void typeSearchLine(String searchLine){

        this.waitForElementAndClear(SEARCH_INPUT, "Can not write given value to search field",  5);
        this.waitForElementAndSendkeys(SEARCH_INPUT, "Can not write given value to search field",  searchLine, 5);
    }

    public void waitForSearchResult(String nameOfArticle){
        String searchElementXpath = getResultSubstring(nameOfArticle);
        this.waitForElementPresent(searchElementXpath, "Cannot find " + nameOfArticle +" on search" , 5);
    }

    public void waitForSearchNotResult(){
        this.waitForElementNotPresent(SEARCH_ARTICLE_ELEMENT, "Founded some elements" , 5);
    }

    public void waitForXButtonToClick(){
        this.waitForElementAndClick(SEARCH_CLOSE_BUTTON, "Can not click to search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CLOSE_BUTTON, "Search cancel button is still present", 5);
    }

    public void waitForNoResultsFoundButton(){
        waitForElementPresent(SEARCH_NO_RESULTS_FOUND_LABEL, "Can not found not results found button", 5);
    }
    public void waitElementOfSearchPresent(String text, String indexStartsWithZero){
        this.waitForSearchElementPresentByText(
                (getElementIndex(indexStartsWithZero)),
                text,
                "Cannot find text "+text+"",
                5
        );
    }

    public void waitElementOfSearchNotPresent(String indexStartsWithZero){
        this.waitForElementNotPresent(
                getElementIndex(indexStartsWithZero),
                "Some elements founded by search",
                5
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        String elementTitle = getElementTitle(title);
        String elementDesc = getElementDesc(description);
        this.waitForElementPresent(elementTitle, "Cannot find " + title +" on search" , 10);
        this.waitForElementPresent(elementDesc, "Cannot find " + description +" on search" , 10);
    }

    public void clickByArticleWithSubString(String substring){
        String searchElementXpath = getResultSubstring(substring);
        this.waitForElementAndClick(searchElementXpath, "Cannot click " + substring +" on search" , 10);
    }

    public void clickToXBtn(){
        this.waitForElementAndClick(SEARCH_CLOSE_BUTTON, "Can not find search button", 5);
    }

    public int getAmountOfSearchArticles(){
        this.waitForElementPresent(
                SEARCH_ARTICLE_ELEMENT,
                "Can not find any results",
                5
        );
        return this.getCountOfElements(SEARCH_ARTICLE_ELEMENT);
    }

}
