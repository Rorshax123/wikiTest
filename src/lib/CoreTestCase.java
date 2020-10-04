package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String
            APPIUM_URL = "http://127.0.0.1:4723/wd/hub",
            PLATFORM_ANDROID = "android",
            PLATFORM_IOS = "ios";

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        driver = this.getDriverByPlatformEnv(capabilities);
        screenRotationPortrait();
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception{
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidDevice");
            capabilities.setCapability("platformVersion", "8.1.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/ailus/Desktop/wikiTest/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 11");
            capabilities.setCapability("platformVersion", "13.0");
            capabilities.setCapability("app", "/Users/ailus/Desktop/wikiTest/apks/Wikipedia.app");
        } else {
            throw new Exception("Can not get run platform from env variable. Platform variable " + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv(DesiredCapabilities caps) throws Exception{


        String platform = System.getenv("PLATFORM");

        if (platform.equals(PLATFORM_ANDROID)){
            return new AndroidDriver(new URL(APPIUM_URL), caps);
        }else if (platform.equals(PLATFORM_IOS)){
            return new IOSDriver(new URL(APPIUM_URL), caps);
        }else {
            throw new Exception("Can not get run driver from env variable. Platform variable " + platform);
        }
    }
}
