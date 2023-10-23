package lib.UI;

import io.appium.java_client.AppiumDriver;

public class SkipPageObject extends MainPageObject {

    private static final String
            SKIP_ELEMENT = "xpath://*[contains(@text,'Skip')]";
    public SkipPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void initSkipOnboarding()
    {
        this.waitForElementAndClick(SKIP_ELEMENT, "Cannot find button Skip",5);
    }
}
