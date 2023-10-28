package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE_IN_FOLDER_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING_TITLE}']";
        BUTTON_DELETE = "xpath://XCUIElementTypeButton[@name='swipe action delete']";
        SUBTITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING_SUBTITLE}']";
    }
    public iOSArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
