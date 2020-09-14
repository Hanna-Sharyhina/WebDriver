package com.training.hardcore.page.googlecloud;

import com.training.hardcore.page.tenminuteemail.TenMinuteEmailHomePage;
import com.training.hardcore.page.tenminuteemail.TenMinutePageWithReceivedEmail;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class PricingCalculatorResultPage {

    private final WebDriver driver;

    public PricingCalculatorResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//iframe[@src = '/products/calculator/index_ad8ca20a6d1799e286a0c0839aeb86ca523afe927b04501d8ba77dc59e5b8523.frame']")
    private WebElement firstInnerFrame;

    @FindBy(id = "myFrame")
    private WebElement secondInnerFrame;

    @FindBy(xpath = "//button[@id ='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//input[@type = 'email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@aria-label = 'Send Email']")
    private WebElement sendEmailButton;


    public PricingCalculatorResultPage openEmailYourEstimateWindow() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(emailEstimateButton)).click();
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",
                        emailInput);
        return this;
    }

    public TenMinuteEmailHomePage openPageWithTemporaryEmail() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        switchBetweenTabs(1);
        return new TenMinuteEmailHomePage(driver, this);
    }

    public PricingCalculatorResultPage switchToInnerFrame() {
        driver.switchTo().frame(firstInnerFrame);
        driver.switchTo().frame(secondInnerFrame);
        return this;
    }

    public PricingCalculatorResultPage addEmailToEmailField() {
        WebElement element = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type = 'email']")));
        element.sendKeys(Keys.CONTROL + "v");
        return this;
    }

    public TenMinutePageWithReceivedEmail sendEmail() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(sendEmailButton)).click();
        switchBetweenTabs(1);
        return new TenMinutePageWithReceivedEmail(driver);
    }

    public void switchBetweenTabs(int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
    }
}
