package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
                ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text";
                ARTICLE_FOOTER = "xpath://*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']";
                MORE_OPTIONS = "xpath://android.widget.ImageView[@content-desc='More options']";
                /*RL reading list*/
                ADD_TO_RL = "xpath://*[@text='Add to reading list']";
                ARTICLE_GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
                INPUT_FIELD_OF_ADD_RL = "id:org.wikipedia:id/text_input";
                OK_BUTTON = "xpath://*[@text='OK']";
                ARTICLE_CLOSE = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                ARTICLE_TITLE_WITH_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='{SUBSTRING}']";
                READING_LIST_TITLE_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/item_title'][@text='{SUBSTRING}']";
    }
    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
