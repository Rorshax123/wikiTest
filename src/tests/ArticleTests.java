package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
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

    @Test
    public void testSaveTwoArticles(){

        //Вот тут много субстрингами пользовался

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String nameOfFolder = "My reading list";
        ArticlePageObject.waitForArticleTitleWithSubString("Java (programming language)");
        ArticlePageObject.addArticleFirstTimeToReadingList(nameOfFolder);
        ArticlePageObject.closeTheArticleWithX();

        //Add second article

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubString("Appius Claudius Caecus");

        ArticlePageObject.waitForArticleTitleWithSubString("Appius Claudius Caecus");
        ArticlePageObject.addArticleToReadingList(nameOfFolder);
        ArticlePageObject.closeTheArticleWithX();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickToMyListsButton();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        MyListsPageObject.clickToMyListByName(nameOfFolder);
        MyListsPageObject.waitForArticleNameOnRLPresent("Appius Claudius Caecus");
        MyListsPageObject.waitForArticleNameOnRLPresent("Java (programming language)");
        MyListsPageObject.deleteArticleFromListByName("Java (programming language)");
        MyListsPageObject.waitForArticleNameOnRLNotPresent("Java (programming language)");
        MyListsPageObject.waitForArticleNameOnRLPresent("Appius Claudius Caecus");
        MyListsPageObject.clickToArticleOnRL("Appius Claudius Caecus");

        ArticlePageObject.waitForArticleTitleWithSubString("Appius Claudius Caecus");

    }
}
