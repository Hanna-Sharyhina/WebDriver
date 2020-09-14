package com.training.hardcore.page.tenminuteemail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TenMinutePageWithReceivedEmail {

    private final WebDriver driver;

    public TenMinutePageWithReceivedEmail(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public TenMinutePageWithReceivedEmail openReceivedEmail() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(90))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        WebElement receivedMessageString = wait
                .until(driver -> driver.findElement(By.xpath("//div[@class = 'message_top']")));
        receivedMessageString.click();
        return this;
    }

    public String getTotalCostFromEmail() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Estimated Monthly Cost')]")))
                .getText().split(" ")[4];
    }
}
