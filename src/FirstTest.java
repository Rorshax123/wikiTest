import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void firstTest() {


        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );


        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find text Object-oriented programming language",
                5
        );

    }

    @Test
    public void testCancelButton() {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search field",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button",
                5

        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "the button expected",
                5
        );

    }

    @Test
    public void testCompareArticleTitle() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5

        );

        WebElement titleElement = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title of object oriented programming",
                15
        );

        String articleTitle = titleElement.getAttribute("text");

        Assert.assertEquals(
                "Founded unexpected article",
                "Java (programming language)",
                articleTitle
        );


    }

    @Test
    public void testSwipeArticle() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5

        );

        WebElement titleElement = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title of object oriented programming",
                15
        );

        MainPageObject.swipeUp(2000);


    }

    @Test
    public void testSwipeArticleUntilFindTheElement() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Appium",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium'",
                5

        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='Appium']"),
                "Cannot find title of object Appium",
                15
        );

        MainPageObject.swipeUntilFindFooter(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']"),
                "Can not find element end of the article",
                20
        );

    }


    @Test
    public void testToAddNewElementToListAndDeleteIt() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5

        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id ='org.wikipedia:id/view_page_title_text'][@text='Java (programming language)']"),
                "Cannot find title of object oriented programming",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find more options button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find add to reading list button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find got it button",
                5
        );


        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find the input field",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find the input field",
                "Java programming language1",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find OK button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My lists button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@resource-id='org.wikipedia:id/item_container']"),
                "Cannot find My reading list  textview",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find added article",
                5
        );

        MainPageObject.swipeLeftFromRight(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find swipe element",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find added article",
                5
        );


    }

    @Test
    public void testSerachGiveTheResults() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        String valeOfSearch = "Linkin Park Discography";
        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                valeOfSearch,
                5
        );

        String xpathOfElement = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        MainPageObject.waitForElementPresent(
                By.xpath(xpathOfElement),
                "Can not find any results for " + valeOfSearch,
                5
        );

        int elementsSize = MainPageObject.countElements(By.xpath(xpathOfElement));
        Assert.assertTrue(
                "Can not find any results",
                elementsSize > 0
        );

    }

    @Test
    public void testArticleRotation() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        String valeOfSearch = "java";
        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                valeOfSearch,
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5
        );

        String elementBeforeRotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find any element",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);


        String elementAfterRotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find any element",
                15
        );

        Assert.assertEquals(
                "The asserted elements not equals",
                elementAfterRotation,
                elementBeforeRotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String elementAfterSecondRotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find any element",
                15
        );

        Assert.assertEquals(
                "The asserted elements not equals",
                elementAfterRotation,
                elementAfterSecondRotation
        );

    }

    @Test
    public void testArticleAfterReturningFromBackground(){

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        String valeOfSearch = "java";
        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                valeOfSearch,
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5
        );

        driver.runAppInBackground(5);


        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button after returning from background",
                5
        );
    }

    @Test
    public void testCancelSearch() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "google",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='American multinational Internet and technology corporation']"),
                "Cannot find text American multinational Internet and technology corporation",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Web product from Google Inc. which provides detailed ground imagery']"),
                "Cannot find text Web product from Google Inc. which provides detailed ground imagery",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button",
                5

        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Web browser developed by Google']"),
                "the button expected",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Web product from Google Inc. which provides detailed ground imagery']"),
                "the button expected",
                5
        );

    }

    @Test
    public void testFindKeyWord() {

        String text = "Google";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                text,
                5
        );

        MainPageObject.assertElementHasGivenText(
                "The given text not found",
                5,
                text,
                0
        );

        MainPageObject.assertElementHasGivenText(
                "The given text not found",
                5,
                text,
                1
        );

        MainPageObject.assertElementHasGivenText(
                "The given text not found",
                5,
                text,
                2
        );

    }

    @Test
    public void testSaveTwoArticles(){

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5

        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id ='org.wikipedia:id/view_page_title_text'][@text='Java (programming language)']"),
                "Cannot find title of object oriented programming",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find more options button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find add to reading list button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find got it button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find OK button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );

        //Add second article

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Appium",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Appius Claudius Caecus']"),
                "Cannot find 'Appius Claudius Caecus' button",
                5

        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id ='org.wikipedia:id/view_page_title_text'][@text='Appius Claudius Caecus']"),
                "Cannot find title Appius Claudius Caecus",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find more options button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find add to reading list button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title'][@text='My reading list']"),
                "Cannot find My reading list",
                5
        );
//

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My lists button",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@resource-id='org.wikipedia:id/item_container']"),
                "Cannot find My reading list  textview",
                5
        );


        //check the the articles saved or not
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Appius Claudius Caecus']"),
                "Cannot find added article",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find added article",
                5
        );

        //delete java programming language article
        MainPageObject.swipeLeftFromRight(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find swipe element",
                5
        );

        // java article should deleted
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find added article",
                5
        );

        //test second element should not deleted
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Appius Claudius Caecus']"),
                "Cannot find added article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Appius Claudius Caecus']"),
                "Cannot find added article",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='Appius Claudius Caecus']"),
                "The title of article not found",
                10
        );

    }

    @Test
    public void testAssertTitle() throws InterruptedException {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        MainPageObject.waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5

        );

        MainPageObject.assertElementPresent(

                By.id("org.wikipedia:id/view_page_title_text"),
                "Java (programming language)"
        );
    }

}
