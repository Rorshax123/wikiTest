package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchElement() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    public void testCancelButton() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForXButtonToClick();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
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
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("asdgfasdg");
        SearchPageObject.waitForNoResultsFoundButton();
        SearchPageObject.waitForSearchNotResult();
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

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

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(keyWord);
        SearchPageObject.waitElementOfSearchPresent(keyWord.toLowerCase(), "0");
        SearchPageObject.waitElementOfSearchPresent(keyWord.toLowerCase(), "1");
        SearchPageObject.waitElementOfSearchPresent(keyWord.toLowerCase(), "2");

    }

    @Test
    public void testAssertTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleTitleWithSubString("Java (programming language)");

    }

    @Test
    public void testSearchByTitleAndDesc(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
    }

}
