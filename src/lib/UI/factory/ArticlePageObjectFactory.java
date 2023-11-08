package lib.UI.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.android.AndroidArticlePageObject;
import lib.UI.android.AndroidSearchPageObject;
import lib.UI.ios.iOSArticlePageObject;
import lib.UI.ios.iOSSearchPageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else {
            return new iOSArticlePageObject(driver);
        }
    }
}
