package tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import lib.UI.SkipPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch()
    {
        SkipPageObject SkipPageObject = new SkipPageObject(driver);
        SkipPageObject.initSkipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        String search_expected_text = "Object-oriented programming language";
        SearchPageObject.waitForSearchResult(search_expected_text);
    }
    @Test
    public void testCancelSearch()
    {
        SkipPageObject SkipPageObject = new SkipPageObject(driver);
        SkipPageObject.initSkipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchInputHasText()
    {
        SkipPageObject SkipPageObject = new SkipPageObject(driver);
        SkipPageObject.initSkipOnboarding();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String expected_text = "Search Wikipedia";
        SearchPageObject.searchExpectedText(expected_text);
    }

    @Test
    public void testClearSearch()
    {
        SkipPageObject SkipPageObject = new SkipPageObject(driver);
        SkipPageObject.initSkipOnboarding();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found some results ",
                amount_of_search_results>1
        );
        SearchPageObject.clearSearchPhrase();
        SearchPageObject.searchNothingResult();
    }

    @Test
    public void testFindSearchResult()
    {
        SkipPageObject SkipPageObject = new SkipPageObject(driver);
        SkipPageObject.initSkipOnboarding();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.findWordInSearchResult(search_line);
    }
}
