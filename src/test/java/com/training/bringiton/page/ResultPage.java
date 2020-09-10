package com.training.bringiton.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {
    private final WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getResultPageHeadLineText() {
        return getElementFromResultPage("//div[@class = 'info-top']/h1");
    }

    public String getSyntaxHighlightingFromResultPage() {
        return getElementFromResultPage("//div[@class = 'left']/a[@class = 'btn -small h_800']");
    }

    public String getPastedCodeFromResultPage() {
        return getElementFromResultPage("//div[@class = 'source']");
    }

    public String getElementFromResultPage(String xpath) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText();
    }
}
