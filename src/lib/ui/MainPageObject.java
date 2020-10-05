package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;


    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }


    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public void waitForSearchElementPresentByText(String locator,String text, String errorMessage, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(locator, errorMessage, 5);
        String elementText = element.getText().toLowerCase();
        Assert.assertTrue(elementText.contains(text));

    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement waitForElementAndSendkeys(String locator, String errorMessage, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void swipeUp(int timeInSeconds) {

        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width * 0.05);

        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);


        action
                .press(PointOption.point(x,startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeInSeconds)))
                .moveTo(PointOption.point(x,endY))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUntilFindFooter(String locator, String erorrMessage, int maxSwipes) {

        int alreadySwiped = 0;
        By by = this.getLocatorByString(locator);

        while (driver.findElements(by).size() == 0) {

            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up \n" + erorrMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }

    }

    public void swipeUpTillElementFound(String locator, String erorrMessage, int maxSwipes){
        int alreadySwiped = 0;
        while (!this.isElementLocatedOnScreen(locator)){
            if (alreadySwiped > maxSwipes)
            {
                Assert.assertTrue(erorrMessage, this.isElementLocatedOnScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }


    public boolean isElementLocatedOnScreen(String locator){
        int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element by locator",1 ).getLocation().getY();
        int screenSizeByY = driver.manage().window().getSize().getHeight();

        return elementLocationByY < screenSizeByY;
    }

    public void swipeLeftFromRight(String locator, String errorMessage, long timeOutInSeconds) {

        WebElement element = waitForElementPresent(locator,
                errorMessage,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(timeOutInSeconds)));
        if (Platform.getInstance().isAndroid()){
            action.moveTo(PointOption.point(left_x, middle_y));
        } else {
            int ofsetX = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(ofsetX, 0));
        }
        action.release();
        action.perform();

    }

    public void clickElementOnRigtUpperCorner(String locator, String errorMessage){
        WebElement element = waitForElementPresent(locator + "/..", errorMessage, 5);

        int rightX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        int width = element.getSize().getWidth();

        int pointToClickX = (rightX + width) - 3;
        int pointToClickY = middleY;
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();
    }

    public void assertElementPresent(String locator, String titleOfArticle){

        WebElement element = waitForElementPresent(locator, "Can not find element by " + locator, 5);

        String titleText = element.getText();

        Assert.assertEquals("Article elements not equal", titleText, titleOfArticle);


    }

    public int getCountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public String waitForElementAndGetAttribute( String  locator, String attribute, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresent(locator, errorMessage, 5);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locatorWithtype){
        String[] explodedLocator = locatorWithtype.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")){
            return By.xpath(locator);
        }else if (byType.equals("id")){
            return By.id(locator);
        }else {
            throw new IllegalArgumentException("Can not get type of locator " + locatorWithtype);
        }
    }

}
