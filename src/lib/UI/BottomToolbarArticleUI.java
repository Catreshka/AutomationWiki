package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class BottomToolbarArticleUI extends MainPageObject {

    protected static String
            BUTTON_PAGE_SAVE,
            BUTTON_ADD_TO_LIST,
            INPUT_NAME_OF_FOLDER,
            BUTTON_OK,
            NAME_OF_EXIST_LIST_TPL,
            BUTTON_VIEW_LIST,
            BUTTON_CREATE_NEW_LIST;

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
        if (Platform.getInstance().isiOS()) {
            this.waitForElementAndClick(BUTTON_ADD_TO_LIST, "Cannot find button 'Add to list'", 5);
        }
    }

    public void createMyList(String name_of_folder)
    {
        if (Platform.getInstance().isiOS()) {
            this.waitForElementAndClick(BUTTON_CREATE_NEW_LIST, "Cannot find button 'Create new list'",15);
        }
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
