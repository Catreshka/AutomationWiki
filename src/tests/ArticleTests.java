package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.*;
import lib.UI.factory.ArticlePageObjectFactory;
import lib.UI.factory.BottomToolbarArticleUIFactory;
import lib.UI.factory.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String searchWord = "Appium";
        String nameArticleOne = "Appium";
        String nameArticleTwo = "Appius Claudius Caecus";
        String subArticleTwo ="Roman statesman";

        SearchPageObject.typeSearchLine(searchWord);
        SearchPageObject.clickByArticleWithSubstring(nameArticleOne);

        String name_folder = "My first folder";

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        BottomToolbarArticleUI BottomToolbarArticleUI = BottomToolbarArticleUIFactory.get(driver);

        BottomToolbarArticleUI.addToList();
        BottomToolbarArticleUI.createMyList(name_folder);

        SearchPageObject.clickCancelSearch();
        SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);

        BottomToolbarArticleUI.addToList();
        BottomToolbarArticleUI.addToExistList(name_folder);
        BottomToolbarArticleUI.viewExistListAfterSave();

        ArticlePageObject.swipeElementDelete(nameArticleTwo);
        SearchPageObject.waitForSearchNoResult(nameArticleTwo);

        if (Platform.getInstance().isAndroid()) {
            String article_title_in_folder = ArticlePageObject.getArticleTitleInFolder(nameArticleTwo);
            SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);
            String article_title_in_article = ArticlePageObject.getTitleInArticle();
            assertEquals(
                    "We see unexpected title",
                    article_title_in_article,
                    article_title_in_folder
            );
        } else if (Platform.getInstance().isiOS()) {
            String article_subtitle_in_folder = ArticlePageObject.getArticleSubTitle(subArticleTwo);
            SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);
            String article_subtitle_in_article = ArticlePageObject.getArticleSubTitle(subArticleTwo);

            assertEquals(
                    "We see unexpected subtitle",
                    article_subtitle_in_folder,
                    article_subtitle_in_article
            );
        }
    }

    @Test
    public void testFindTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);

        String article_title = "Appium";
        SearchPageObject.clickByArticleWithSubstring(article_title);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElementInArticleWithoutTimeout();
    }
}
