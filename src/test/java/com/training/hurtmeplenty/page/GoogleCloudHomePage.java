package com.training.hurtmeplenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage {

    private static final String HOME_PAGE_URL = "https://cloud.google.com/";
    private final WebDriver driver;
    private static final String SEARCH_BUTTON_XPATH = "//div[@class = 'devsite-search-container']";

    @FindBy(xpath = "//input[@aria-label = 'Search box']")
    private WebElement searchInput;

    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public SearchResultsPage searchForTerms(String term) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_BUTTON_XPATH))).click();
        searchInput.sendKeys(term);
        searchInput.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
