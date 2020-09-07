import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
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

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/hasan/Desktop/wikiTest/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {


        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );


        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find text Object-oriented programming language",
                5
        );

    }

    @Test
    public void testCancelButton() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search field",
                5

        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button",
                5

        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "the button expected",
                5
        );

    }

    @Test
    public void testCompareArticleTitle() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5

        );

        WebElement titleElement = waitForElementPresent(
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

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5

        );

        WebElement titleElement = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title of object oriented programming",
                15
        );

        swipeUp(2000);


    }

    @Test
    public void testSwipeArticleUntilFindTheElement() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Appium",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium'",
                5

        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='Appium']"),
                "Cannot find title of object Appium",
                15
        );

        swipeUntilFindFooter(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']"),
                "Can not find element end of the article",
                20
        );

    }


    @Test
    public void testToAddNewElementToListAndDeleteIt() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "Java",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5

        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title of object oriented programming",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find more options button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find add to reading list button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find got it button",
                5
        );


        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find the input field",
                5
        );

        waitForElementAndSendkeys(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find the input field",
                "Java programming language1",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My lists button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@resource-id='org.wikipedia:id/item_container']"),
                "Cannot find My reading list  textview",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find added article",
                5
        );

        swipeLeftFromRight(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find swipe element",
                5
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find added article",
                5
        );


    }

    @Test
    public void testSerachGiveTheResults() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        String valeOfSearch = "Linkin Park Discography";
        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                valeOfSearch,
                5
        );

        String xpathOfElement = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(
                By.xpath(xpathOfElement),
                "Can not find any results for " + valeOfSearch,
                5
        );

        int elementsSize = countElements(By.xpath(xpathOfElement));
        Assert.assertTrue(
                "Can not find any results",
                elementsSize > 0
        );

    }

    @Test
    public void testArticleRotation() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        String valeOfSearch = "java";
        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                valeOfSearch,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5
        );

        String elementBeforeRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find any element",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);


        String elementAfterRotation = waitForElementAndGetAttribute(
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

        String elementAfterSecondRotation = waitForElementAndGetAttribute(
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

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        String valeOfSearch = "java";
        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                valeOfSearch,
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button",
                5
        );

        driver.runAppInBackground(5);


        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' button after returning from background",
                5
        );
    }

    @Test
    public void cancelSearch() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                "google",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='American multinational Internet and technology corporation']"),
                "Cannot find text Object-oriented programming language",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Web product from Google Inc. which provides detailed ground imagery']"),
                "Cannot find text Object-oriented programming language",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Web browser developed by Google']"),
                "Cannot find text Object-oriented programming language",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button",
                5

        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Web browser developed by Google']"),
                "the button expected",
                5
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Web product from Google Inc. which provides detailed ground imagery']"),
                "the button expected",
                5
        );

    }

    @Test
    public void testFindKeyWord() {

        String text = "Google";

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search wikipedia",
                5
        );

        waitForElementAndSendkeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Can not find search element",
                text,
                5
        );

        assertElementHasGivenText(
                "The given text not found",
                5,
                text,
                0
        );

        assertElementHasGivenText(
                "The given text not found",
                5,
                text,
                1
        );

        assertElementHasGivenText(
                "The given text not found",
                5,
                text,
                2
        );

    }


    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement waitForElementAndSendkeys(By by, String errorMessage, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private void assertElementHasGivenText(String errorMessage, long timeOutInSeconds, String text, int index) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container' and @index=" + index + "]//*[@resource-id='org.wikipedia:id/page_list_item_title']")));
        WebElement element = driver.findElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container' and @index=" + index + "]//*[@resource-id='org.wikipedia:id/page_list_item_title']"));
        Assert.assertTrue("The expected button has not " + text + " text", element.getText().contains(text));
    }

    private void swipeUp(int timeInSeconds) {

        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;

        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);


        action.press(x, startY).waitAction(timeInSeconds).moveTo(x, endY).release().perform();
    }


    private void swipeUpQuick() {
        swipeUp(200);
    }

    private void swipeUntilFindFooter(By by, String erorrMessage, int maxSwipes) {


        int alreadySwiped = 0;

        while (driver.findElements(by).size() == 0) {

            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up \n" + erorrMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }

    }

    private void swipeLeftFromRight(By by, String errorMessage, long timeOutInSeconds) {

        WebElement element = waitForElementPresent(by,
                errorMessage,
                5);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    private int countElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private String waitForElementAndGetAttribute( By by, String attribute, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        return element.getAttribute(attribute);
    }
}
