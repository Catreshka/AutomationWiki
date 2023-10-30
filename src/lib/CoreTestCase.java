package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.UI.MainPageObject;
import lib.UI.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForApp();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    private void skipWelcomePageForApp()
    {
        if (Platform.getInstance().isiOS()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        } else if (Platform.getInstance().isAndroid()) {
            MainPageObject MainPageObject = new MainPageObject(driver);
            MainPageObject.clickSkip();
        }
    }
}
