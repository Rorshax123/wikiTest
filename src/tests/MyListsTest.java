package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {
    @Test
    public void testToAddNewElementToListAndDeleteIt() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String nameOfFolder = "My reading list";
        ArticlePageObject.waitForArticleTitleWithSubString("Java (programming language)");
        ArticlePageObject.addArticleFirstTimeToReadingList(nameOfFolder);
        ArticlePageObject.closeTheArticleWithX();


        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickToMyListsButton();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.clickToMyListByName("My reading list");
        MyListsPageObject.waitForArticleNameOnRL("Java (programming language)");
        MyListsPageObject.deleteArticleFromListByName("Java (programming language)");
        MyListsPageObject.waitForArticleNotPresentOnRL();

    }
}
