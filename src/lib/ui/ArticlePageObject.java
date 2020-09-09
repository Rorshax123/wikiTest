package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
        ARTICLE_TITLE = "org.wikipedia:id/view_page_title_text",
        ARTICLE_FOOTER = "//*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']",
        MORE_OPTIONS = "//android.widget.ImageView[@content-desc='More options']",
        /*RL reading list*/
        ADD_TO_RL = "//*[@text='Add to reading list']",
        ARTICLE_GOT_IT_BUTTON = "org.wikipedia:id/onboarding_button",
        INPUT_FIELD_OF_ADD_RL = "org.wikipedia:id/text_input",
        OK_BUTTON = "//*[@text='OK']",
        ARTICLE_X_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        ARTICLE_TITLE_WITH_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='{SUBSTRING}']",
        READING_LIST_TITLE_TPL = "//*[@resource-id = 'org.wikipedia:id/item_title'][@text='{SUBSTRING}']";

    /*TEMPLATES*/
    private static String getResultSubstring(String substring){
        return ARTICLE_TITLE_WITH_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getNameOfRL(String substring){
        return READING_LIST_TITLE_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES*/

    public WebElement waitForArticleTitle(){
        WebElement element = waitForElementPresent(By.id(ARTICLE_TITLE), "Can not find article title", 10);
        return element;
    }

    public void waitForArticleTitleWithSubString(String substring){
        String articleTitleWithSubstring = getResultSubstring(substring);
        this.waitForElementPresent(By.xpath(articleTitleWithSubstring), "Can not find " + substring + " article title", 10);
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForArticleTitle();
        return titleElement.getAttribute("text");
    }

    public void swipeArticleUntilFindFooter(){
        this.swipeUntilFindFooter(By.xpath(ARTICLE_FOOTER), "Can not find swipe to footer", 20);
    }

    public void addArticleFirstTimeToReadingList(String nameOfFolder){

        this.waitForElementAndClick(
                By.xpath(MORE_OPTIONS),
                "Cannot find more options button",
                5
        );

        this.waitForElementAndClick(
                By.xpath(ADD_TO_RL),
                "Cannot find add to reading list button",
                5
        );

        this.waitForElementAndClick(
                By.id(ARTICLE_GOT_IT_BUTTON),
                "Cannot find got it button",
                5
        );


        this.waitForElementAndClear(
                By.id(INPUT_FIELD_OF_ADD_RL),
                "Cannot find the input field",
                5
        );

        this.waitForElementAndSendkeys(
                By.id(INPUT_FIELD_OF_ADD_RL),
                "Cannot find the input field",
                nameOfFolder,
                5
        );

        this.waitForElementAndClick(
                By.xpath(OK_BUTTON),
                "Cannot find OK button",
                5
        );

    }

    public void addArticleToReadingList(String nameOfFolder){


        this.waitForElementAndClick(
                By.xpath(MORE_OPTIONS),
                "Cannot find more options button",
                5
        );

        this.waitForElementAndClick(
                By.xpath(ADD_TO_RL),
                "Cannot find add to reading list button",
                5
        );

        this.waitForElementAndClick(
                By.xpath(getNameOfRL(nameOfFolder)),
                "Cannot find " + nameOfFolder + " list",
                5
        );

    }

    public void closeTheArticleWithX(){
        this.waitForElementAndClick(
                By.xpath(ARTICLE_X_BUTTON),
                "Cannot find X button",
                5
        );
    }
}
