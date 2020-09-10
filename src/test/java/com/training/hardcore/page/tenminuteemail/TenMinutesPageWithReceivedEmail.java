package com.training.hardcore.page.tenminuteemail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TenMinutesPageWithReceivedEmail {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@class = 'message_top']")
    private WebElement receivedMessageString;

    public TenMinutesPageWithReceivedEmail(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public TenMinutesPageWithReceivedEmail openReceivedEmail() {
        new WebDriverWait(driver, 90)
                .until(ExpectedConditions.elementToBeClickable(receivedMessageString)).click();
        return this;
    }

    public String getTotalCostFromEmail() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Estimated Monthly Cost')]")))
                .getText().split(" ")[4];
    }
}
