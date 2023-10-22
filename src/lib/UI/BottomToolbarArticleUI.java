package lib.UI;

import io.appium.java_client.AppiumDriver;
public class BottomToolbarArticleUI extends MainPageObject {

    private static final String
            BUTTON_PAGE_SAVE = "id:org.wikipedia:id/page_save",
            BUTTON_ADD_TO_LIST = "xpath://*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']",
            INPUT_NAME_OF_FOLDER = "xpath://*[contains(@text,'Name of this list')]",
            BUTTON_OK = "xpath://*[@resource-id='android:id/button1'][@text='OK']",
            NAME_OF_EXIST_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING_NAME_OF_LIST}']",
            BUTTON_VIEW_LIST = "xpath://*[@resource-id='org.wikipedia:id/snackbar_action'][@text='View list']";

    public BottomToolbarArticleUI(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getNameOfList(String substring)
    {
        return NAME_OF_EXIST_LIST_TPL.replace("{SUBSTRING_NAME_OF_LIST}",substring);
    }
    /*TEMPLATES METHODS */
    public void addToList ()
    {
        this.waitForElementAndClick(BUTTON_PAGE_SAVE, "Cannot find Save button", 5);
        this.waitForElementAndClick(BUTTON_ADD_TO_LIST, "Cannot find button 'Add to list'", 5);
    }

    public void createMyList(String name_of_folder)
    {
        this.waitForElementAndSendKeys(INPUT_NAME_OF_FOLDER, name_of_folder, "Cannot find input for write name of this list", 5);
        this.waitForElementAndClick(BUTTON_OK, "Cannot find button 'OK'", 5);

    }
    public void addToExistList(String substring)
    {
        String name_of_saves_list = getNameOfList(substring);
        this.waitForElementAndClick(name_of_saves_list, "Cannot find folder " + substring, 5);
    }

    public void viewExistListAfterSave()
    {
        this.waitForElementAndClick(BUTTON_VIEW_LIST, "Cannot click on View list", 5);
    }
}
