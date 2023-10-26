package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_AND_INPUT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_LIST = "xpath://XCUIElementTypeOther[2]/XCUIElementTypeCollectionView";
        SEARCH_ITEM = "xpath://XCUIElementTypeOther/following::XCUIElementTypeStaticText[1]";
    }
    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
