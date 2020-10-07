package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "id:Java (programming language)";
        ARTICLE_FOOTER = "id:View article in browser";
        MORE_OPTIONS = "xpath://android.widget.ImageView[@content-desc='More options']";
        /*RL reading list*/
        ADD_TO_RL = "id:Save for later";
        ARTICLE_CLOSE = "id:Back";
        ARTICLE_TITLE_WITH_SUBSTRING_TPL = "id:{SUBSTRING}";
        READING_LIST_TITLE_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/item_title'][@text='{SUBSTRING}']";
        ARTICLE_X_BTN = "id:places auth close";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}