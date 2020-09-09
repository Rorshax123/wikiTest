package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
        ARTICLE_NAME_ON_RL = "//*[@text='Java (programming language)']",
        MY_LIST_LINK_BY_NAME_TPL = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']",
        ARTICLE_NAME_ON_RL_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";

    /*TEMPLATES*/
    private static String getResultSubstringOfListName(String substring){
        return MY_LIST_LINK_BY_NAME_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSubstringOfArticleName(String substring){
        return ARTICLE_NAME_ON_RL_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES*/

    public void clickToMyListByName(String substring){

        String listName = getResultSubstringOfListName(substring);
        this.waitForElementAndClick(
                By.xpath(listName),
                "Cannot find My reading list  textview",
                5
        );
    }

    public void waitForArticleNameOnRLPresent(String substring){

        String articleName = getResultSubstringOfArticleName(substring);
        this.waitForElementPresent(
                By.xpath(articleName),
                "Cannot find added article",
                10
        );
    }

    public void waitForArticleNameOnRLNotPresent(String substring){

        String articleName = getResultSubstringOfArticleName(substring);
        this.waitForElementNotPresent(
                By.xpath(articleName),
                "Cannot find added article",
                5
        );
    }

    public void deleteArticleFromListByName(String nameOfArticle){
        String articleName = getResultSubstringOfArticleName(nameOfArticle);
        this.swipeLeftFromRight(
                By.xpath(articleName),
                "Cannot find swipe element",
                5
        );
    }

    public void clickToArticleOnRL(String substring){
        String articleName = getResultSubstringOfArticleName(substring);
        this.waitForElementAndClick(
                By.xpath(articleName),
                "Cannot find added article",
                5
        );
    }

}
