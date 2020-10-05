package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;

abstract public class MyListsPageObject extends MainPageObject{
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
        ARTICLE_NAME_ON_RL,
        MY_LIST_LINK_BY_NAME_TPL,
        ARTICLE_NAME_ON_RL_TPL;

    /*TEMPLATES*/
    private static String getResultSubstringOfListName(String substring){
        return MY_LIST_LINK_BY_NAME_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSubstringOfArticleName(String substring){
        return ARTICLE_NAME_ON_RL_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES**/

    public void clickToMyListByName(String substring){
        String listName = getResultSubstringOfListName(substring);
        this.waitForElementAndClick(
                (listName),
                "Cannot find My reading list  textview",
                5
        );
    }

    public void waitForArticleNameOnRLPresent(String substring){
        String articleName = getResultSubstringOfArticleName(substring);
        this.waitForElementPresent(
                (articleName),
                "Cannot find added article",
                10
        );
    }

    public void waitForArticleNameOnRLNotPresent(String substring){
        String articleName = getResultSubstringOfArticleName(substring);
        this.waitForElementNotPresent(
                (articleName),
                "Cannot find added article",
                5
        );
    }

    public void deleteArticleFromListByName(String nameOfArticle){
        String articleName = getResultSubstringOfArticleName(nameOfArticle);
        this.swipeLeftFromRight(
                (articleName + "/.."),
                "Cannot find swipe element",
                5
        );
        if (Platform.getInstance().isIOS()){
            this.clickElementOnRigtUpperCorner(articleName, "Can not tap to delete btn");
        }

        this.waitForElementNotPresent(articleName, "Element expected on RL " + nameOfArticle, 5);
    }

    public void clickToArticleOnRL(String substring){
        String articleName = getResultSubstringOfArticleName(substring);
        this.waitForElementAndClick(
                articleName,
                "Cannot find added article",
                5
        );
    }

}
