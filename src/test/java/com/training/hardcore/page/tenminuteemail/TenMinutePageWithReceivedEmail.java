package com.training.hardcore.page.tenminuteemail;

import com.training.hardcore.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TenMinutePageWithReceivedEmail extends AbstractPage {

    private final By receivedMessageLineLocator = By.xpath("//div[@class = 'message_top']");
    private final By estimatedMonthlyCostStringLocator = By.xpath("//h2[contains(text(), 'Estimated Monthly Cost')]");

    public TenMinutePageWithReceivedEmail(WebDriver driver) {
        super(driver);
    }

    public TenMinutePageWithReceivedEmail openReceivedEmail() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(90))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
        WebElement receivedMessageString = wait
                .until(driver -> driver.findElement(receivedMessageLineLocator));
        receivedMessageString.click();
        return this;
    }

    public String getTotalCostFromEmail() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(estimatedMonthlyCostStringLocator))
                .getText().split(" ")[4];
    }
}
