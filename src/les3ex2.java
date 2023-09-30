import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class les3ex2 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/bashkirceva/AutomationWiki/AutomationWiki/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void ElementHasText()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                5
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Search…",
                "Element does not equal Search…"
        );
    }
    private WebElement waitForElementPresent(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Cannot find element" + by);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by) {
        return waitForElementPresent(by,5);
    }
    private WebElement waitForElementAndClick(By by, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by,timeoutInSeconds);
        element.click();
        return element;
    }

    private void assertElementHasText(By by, String expected_result, String error_message)
    {
        WebElement element = waitForElementPresent(by);
        String elementText = element.getAttribute("text");
        Assert.assertEquals(error_message,expected_result,elementText);
    }

}



