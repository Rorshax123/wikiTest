package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchElement() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    public void testCancelButton() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.clickToXBtn();
        SearchPageObject.waitForXButtonToClick();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Linkin Park Discography");
        int elementsSize = SearchPageObject.getAmountOfSearchArticles();
        assertTrue(
                "Can not find any results",
                elementsSize > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("asdgfasdg");
        SearchPageObject.waitForNoResultsFoundButton();
        SearchPageObject.waitForSearchNotResult();
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("google");
        SearchPageObject.waitElementOfSearchPresent("google", "0");
        SearchPageObject.waitElementOfSearchPresent("google", "1");
        SearchPageObject.clickToXBtn();
        SearchPageObject.waitElementOfSearchNotPresent("0");
        SearchPageObject.waitElementOfSearchNotPresent("1");

    }

    @Test
    public void testFindKeyWord() {

        String keyWord = "Google";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(keyWord);
        SearchPageObject.waitElementOfSearchPresent(keyWord.toLowerCase(), "0");
        SearchPageObject.waitElementOfSearchPresent(keyWord.toLowerCase(), "1");
        SearchPageObject.waitElementOfSearchPresent(keyWord.toLowerCase(), "2");

    }

    @Test
    public void testAssertTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForArticleTitleWithSubString("Java (programming language)");

    }


}
