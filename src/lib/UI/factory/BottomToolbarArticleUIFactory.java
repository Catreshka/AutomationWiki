package lib.UI.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.BottomToolbarArticleUI;
import lib.UI.android.AndroidBottomToolbarArticleUI;
import lib.UI.ios.iOSBottomToolbarArticleUI;

public class BottomToolbarArticleUIFactory {
    public static BottomToolbarArticleUI get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidBottomToolbarArticleUI(driver);
        } else {
            return new iOSBottomToolbarArticleUI(driver);
        }
    }
}
