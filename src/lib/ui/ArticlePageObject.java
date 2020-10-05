package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
        ARTICLE_TITLE,
        ARTICLE_FOOTER,
        MORE_OPTIONS,
        /*RL reading list*/
        ADD_TO_RL,
        ARTICLE_GOT_IT_BUTTON,
        INPUT_FIELD_OF_ADD_RL,
        OK_BUTTON,
        ARTICLE_CLOSE,
        ARTICLE_TITLE_WITH_SUBSTRING_TPL,
        READING_LIST_TITLE_TPL,
        ARTICLE_X_BTN;

    /*TEMPLATES*/
    private static String getResultSubstring(String substring){
        return ARTICLE_TITLE_WITH_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getNameOfRL(String substring){
        return READING_LIST_TITLE_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES**/

    public WebElement waitForArticleTitle(){
        WebElement element = waitForElementPresent(ARTICLE_TITLE, "Can not find article title", 10);
        return element;
    }

    public void waitForArticleTitleWithSubString(String substring){
        String articleTitleWithSubstring = getResultSubstring(substring);
        this.waitForElementPresent(articleTitleWithSubstring, "Can not find " + substring + " article title", 10);
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForArticleTitle();
        if (Platform.getInstance().isAndroid())
        {
        return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }

    public void swipeArticleUntilFindFooter(){
        if (Platform.getInstance().isAndroid()){
        this.swipeUntilFindFooter(ARTICLE_FOOTER, "Can not find swipe to footer", 40);
        }
        else {
            this.swipeUpTillElementFound(ARTICLE_FOOTER, "Cannot find swipe to footer", 40);
        }
    }

    public void addArticleFirstTimeToReadingList(String nameOfFolder){

        this.waitForElementAndClick(
                MORE_OPTIONS,
                "Cannot find more options button",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_RL,
                "Cannot find add to reading list button",
                5
        );

        this.waitForElementAndClick(
                ARTICLE_GOT_IT_BUTTON,
                "Cannot find got it button",
                5
        );


        this.waitForElementAndClear(
                INPUT_FIELD_OF_ADD_RL,
                "Cannot find the input field",
                5
        );

        this.waitForElementAndSendkeys(
                INPUT_FIELD_OF_ADD_RL,
                "Cannot find the input field",
                nameOfFolder,
                5
        );

        this.waitForElementAndClick(
                OK_BUTTON,
                "Cannot find OK button",
                5
        );

    }

    public void addArticleToReadingList(String nameOfFolder){
        this.waitForElementAndClick(
                MORE_OPTIONS,
                "Cannot find more options button",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_RL,
                "Cannot find add to reading list button",
                5
        );

        this.waitForElementAndClick(
                getNameOfRL(nameOfFolder),
                "Cannot find " + nameOfFolder + " list",
                5
        );

    }

    public void addArticlesToMySaved() throws Exception {
        this.waitForElementAndClick(ADD_TO_RL, "Can not found add article to rl btn", 5);
        try {
            this.waitForElementAndClick(ARTICLE_X_BTN, "Can not find x button to disappear x btn", 5);
        } catch (Exception e){

        }
    }

    public void closeTheArticle(){
        this.waitForElementAndClick(
                ARTICLE_CLOSE,
                "Cannot find X button",
                5
        );
    }

}
