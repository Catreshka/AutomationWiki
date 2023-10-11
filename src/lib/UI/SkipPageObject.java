package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SkipPageObject extends MainPageObject {

    private static final String
            SKIP_ELEMENT = "//*[contains(@text,'Skip')]";
    public SkipPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void initSkipOnboarding()
    {
        this.waitForElementAndClick(By.xpath(SKIP_ELEMENT), "Cannot find button Skip",5);
    }
}
