import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
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
    public void testSaveTwoArticles(){


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

    @Test
    public void testAssertTitle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForArticleTitleWithSubString("Java (programming language)");

    }

}
