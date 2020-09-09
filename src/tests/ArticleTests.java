package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String articleTitle = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Founded unexpected article",
                "Java (programming language)",
                articleTitle
        );
    }

    @Test
    public void testSwipeArticleUntilFindTheElement() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubString("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.waitForArticleTitleWithSubString("Appium");
        ArticlePageObject.swipeArticleUntilFindFooter();

    }


}
