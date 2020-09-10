package com.training.hardcore.page.googlecloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private final WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PricingCalculatorPage getToRequiredSearchResult(String requiredItem) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By
                                .xpath("//div[@class = 'gsc-results gsc-webResult']//b[contains(text(), '" +
                                        requiredItem + "')]"))).click();
        return new PricingCalculatorPage(driver);
    }
}