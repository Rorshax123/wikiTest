package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStarted extends CoreTestCase {

    @Test
    public void testWelcomePage() {

        if (Platform.getInstance().isAndroid()){
            return;
        }
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitTitleFreeEnc();
        welcomePageObject.clickNext();
        welcomePageObject.waitTitleWaysToExplore();
        welcomePageObject.clickNext();
        welcomePageObject.waitTitleSearchIn300();
        welcomePageObject.clickNext();
        welcomePageObject.waitTitleHelpMakeAppBetter();
        welcomePageObject.clickGetStarted();
    }

}
