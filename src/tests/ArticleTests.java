package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.BottomToolbarArticleUI;
import lib.UI.SearchPageObject;
import lib.UI.SkipPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testSwipeArticle()
    {
        SkipPageObject SkipPageObject = new SkipPageObject(driver);
        SkipPageObject.initSkipOnboarding();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String searchWord = "Appium";
        String nameArticleOne = "Appium";
        String nameArticleTwo = "Appius Claudius Caecus";

        SearchPageObject.typeSearchLine(searchWord);
        SearchPageObject.clickByArticleWithSubstring(nameArticleOne);

        String name_folder = "My first folder";

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        BottomToolbarArticleUI BottomToolbarArticleUI = new BottomToolbarArticleUI(driver);

        BottomToolbarArticleUI.addToList();
        BottomToolbarArticleUI.createMyList(name_folder);

        SearchPageObject.clickCancelSearch();
        SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);

        BottomToolbarArticleUI.addToList();
        BottomToolbarArticleUI.addToExistList(name_folder);
        BottomToolbarArticleUI.viewExistListAfterSave();

        ArticlePageObject.swipeElementDelete(nameArticleOne);
        SearchPageObject.waitForSearchNoResult(nameArticleOne);

        String article_title_in_folder = ArticlePageObject.getArticleTitleInFolder(nameArticleTwo);
        SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);
        String article_title_in_article = ArticlePageObject.getTitleInArticle();
        assertEquals(
                "We see unexpected title",
                article_title_in_article,
                article_title_in_folder
        );
    }

    @Test
    public void testFindTitle()
    {
        SkipPageObject SkipPageObject = new SkipPageObject(driver);
        SkipPageObject.initSkipOnboarding();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);

        String article_title = "Appium";
        SearchPageObject.clickByArticleWithSubstring(article_title);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElementInArticleWithoutTimeout();
    }
}
