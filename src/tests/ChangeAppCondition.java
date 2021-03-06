package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppCondition extends CoreTestCase {
    @Test
    public void testArticleRotation() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String elementBeforeRotation = ArticlePageObject.getArticleTitle();

        screenRotationLandscape();

        String elementAfterRotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "The asserted elements not equals",
                elementAfterRotation,
                elementBeforeRotation
        );

        screenRotationPortrait();

        String elementAfterSecondRotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "The asserted elements not equals",
                elementAfterRotation,
                elementAfterSecondRotation
        );
    }

    @Test
    public void testArticleAfterReturningFromBackground(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        runAppInBackground(5);

        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
