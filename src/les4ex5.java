import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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
public class les4ex5 {
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
        capabilities.setCapability("app", "/Users/bashkirceva/AutomationWiki/AutomationWiki/apks/Wiki.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testSwipeArticle()
    {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_phrase = "Appium";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_phrase,
                "Cannot find search "+search_phrase,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find Appium article in search",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find Save button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),
                "Cannot find button 'Add to list'",
                5
        );

        String name_folder = "My first folder";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Name of this list')]"),
                name_folder,
                "Cannot find input for write name of this list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button1'][@text='OK']"),
                "Cannot find button 'OK'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Navigate arrow",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appius Claudius Caecus']"),
                "Cannot find 'Appius Claudius Caecus' article in search",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find Save button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),
                "Cannot find button 'Add to list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='"+name_folder+"']"),
                "Cannot find folder 'My first folder'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='View list']"),
                "Cannot click on View list",
                5
        );

        String nameArticleOne = "Appium";

        swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + nameArticleOne + "']"),
                "Cannot find " + nameArticleOne + " article in saved folder"
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + nameArticleOne + "']"),
                "Cannot delete " + nameArticleOne + " article",
                5
        );

        String nameArticleTwo = "Appius Claudius Caecus";

        WebElement title_element_in_folder = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + nameArticleTwo + "']"),
                "Cannot find " + nameArticleTwo + " article in folder",
                5
        );

        String article_title_in_folder = title_element_in_folder.getAttribute("text");

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + nameArticleTwo + "']"),
                "Cannot click on " + nameArticleTwo + " article",
                5
        );

        WebElement title_element_in_article = waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View"),
                "Cannot find title " + nameArticleTwo + " in article",
                5
        );

        String article_title_in_article = title_element_in_article.getAttribute("contentDescription");

        Assert.assertEquals(
                "We see unexpected title",
                article_title_in_article,
                article_title_in_folder
        );
    }
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by,String error_message) {
        return waitForElementPresent(by,error_message,5);
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String error_message, long timeoutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y) / 2;
        new TouchAction(driver)
                .press(right_x-10, middle_y)
                .waitAction(300)
                .moveTo(left_x +10, middle_y)
                .release()
                .perform();
    }
}
