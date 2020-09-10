package com.training.icanwin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PastebinResultPage {
    private final WebDriver driver;
    private static final String SUCCESSFUL_NOTE_XPATH = "//div[@class='notice -success -post-view']//b[contains(text(),'NOTE')]";

    public PastebinResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getSuccessfulCreationNote() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUCCESSFUL_NOTE_XPATH)));
    }
}
