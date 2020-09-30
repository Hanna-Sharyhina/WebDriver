package com.training.hardcore.page.googlecloud;

import com.training.hardcore.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage extends AbstractPage {

    private static final String HOME_PAGE_URL = "https://cloud.google.com/";
    private final By searchButtonXpath = By.xpath("//div[@class = 'devsite-search-container']");

    @FindBy(xpath = "//input[@aria-label = 'Search box']")
    private WebElement searchInput;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public SearchResultsPage searchForTerms(String term) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(searchButtonXpath)).click();
        searchInput.sendKeys(term);
        searchInput.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
