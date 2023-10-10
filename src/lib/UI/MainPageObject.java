package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by,error_message,5);
    }
    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
        element.click();
        return element;
    }
    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    public boolean waitForElementNotPresent(By by, String error_message, long timeoutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public void assertElementHasText(By by, String expected_result, String error_message, String assert_error)
    {
        WebElement element = waitForElementPresent(by,error_message);
        String elementText = element.getAttribute("text");
        Assert.assertEquals(error_message,expected_result,elementText);
    }
    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
        element.clear();
        return element;
    }

    public void waitForWordInElement(By externalBy, By internalBy, String error_message, String value) {
        driver.hideKeyboard();
        waitForElementPresent(externalBy, error_message);
        WebElement element= driver.findElement(externalBy);
        List<WebElement> titles= element.findElements(internalBy);
        boolean result = titles.stream().allMatch(selectText -> selectText.getText().contains(value));
        Assert.assertTrue(error_message, result);
    }

    public void swipeElementToLeft(By by, String error_message)
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

    public void assertElementPresent (By by, String error_message)
    {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw new AssertionError(error_message);
        }
    }

    public  int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }
}
