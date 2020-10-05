package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestCase extends TestCase {


    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        screenRotationPortrait();
        this.skipWelcomePageObjectForIOSApp();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();

    }

    protected void screenRotationPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }


    protected void screenRotationLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void runAppInBackground(int seconds){
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private void skipWelcomePageObjectForIOSApp(){
        if (Platform.getInstance().isIOS()){
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }


}
