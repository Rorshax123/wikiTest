import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
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
