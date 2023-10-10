package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE_IN_FOLDER_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING_TITLE}']",
            TITLE_IN_ARTICLE = "//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View";
    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getTitleElement(String substring)
    {
        return TITLE_IN_FOLDER_TPL.replace("{SUBSTRING_TITLE}",substring);
    }
    /*TEMPLATES METHODS */

    public WebElement waitForTitleElementInFolder(String substring)
    {
        String element_result_present_xpath = getTitleElement(substring);
        return this.waitForElementPresent(By.xpath(element_result_present_xpath),"Cannot find " + substring + " article in folder",7);
    }
    public String getArticleTitleInFolder(String substring)
    {
        WebElement title_element_in_folder = waitForTitleElementInFolder(substring);
        return title_element_in_folder.getAttribute("text");
    }

    public WebElement waitForTitleElementInArticle()
    {
        return this.waitForElementPresent(By.xpath(TITLE_IN_ARTICLE),"Cannot find title in article",10);
    }
    public void waitForTitleElementInArticleWithoutTimeout()
    {
        this.assertElementPresent(By.xpath(TITLE_IN_ARTICLE),"Cannot find title in article");
    }
    public String getTitleInArticle()
    {
        WebElement title_element = waitForTitleElementInArticle();
        return title_element.getAttribute("contentDescription");
    }

    public void swipeElementDelete(String substring)
    {
        this.waitForArticleToAppearByTitle(substring);
        String element_for_swipe = getTitleElement(substring);
        this.swipeElementToLeft(By.xpath(element_for_swipe),"Cannot find " + substring + " article in saved folder");
    }

    public void waitForArticleToAppearByTitle(String substring)
    {
        String article_xpath = getTitleElement(substring);
        this.waitForElementPresent(By.xpath(article_xpath),"Cannot find saved article by title " + substring,15);
    }


}
