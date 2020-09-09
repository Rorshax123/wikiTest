package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppCondition extends CoreTestCase {
    @Test
    public void testArticleRotation() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
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

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        runAppInBackground(5);

        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}
