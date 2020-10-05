package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigatioUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    private static final String NAME_OF_FOLDER = "My reading list";

    @Test
    public void testToAddNewElementToListAndDeleteIt() throws Exception {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForArticleTitleWithSubString("Java (programming language)");

        if (Platform.getInstance().isAndroid()){

            ArticlePageObject.addArticleFirstTimeToReadingList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeTheArticle();

        NavigationUI NavigationUI = NavigatioUIFactory.get(driver);
        NavigationUI.clickToMyListsButton();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
        MyListsPageObject.clickToMyListByName("My reading list");
        }
        MyListsPageObject.waitForArticleNameOnRLPresent("Java (programming language)");
        MyListsPageObject.deleteArticleFromListByName("Java (programming language)");
    }

    @Test
    public void testSaveTwoArticles() throws Exception {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForArticleTitleWithSubString("Java (programming language)");
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleFirstTimeToReadingList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeTheArticle();

        //Add second article

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubString("Appius Claudius Caecus");

        ArticlePageObject.waitForArticleTitleWithSubString("Appius Claudius Caecus");
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToReadingList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeTheArticle();

        NavigationUI NavigationUI = NavigatioUIFactory.get(driver);
        NavigationUI.clickToMyListsButton();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.clickToMyListByName(NAME_OF_FOLDER);
        }
        MyListsPageObject.waitForArticleNameOnRLPresent("Appius Claudius Caecus");
        MyListsPageObject.waitForArticleNameOnRLPresent("Java (programming language)");
        MyListsPageObject.deleteArticleFromListByName("Java (programming language)");
        MyListsPageObject.waitForArticleNameOnRLNotPresent("Java (programming language)");
        MyListsPageObject.waitForArticleNameOnRLPresent("Appius Claudius Caecus");
        MyListsPageObject.clickToArticleOnRL("Appius Claudius Caecus");
        ArticlePageObject.waitForArticleTitleWithSubString("Appius Claudius Caecus");

    }
}
