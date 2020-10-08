package com.training.hardcore.page.tenminuteemail;

import com.training.hardcore.page.AbstractPage;
import com.training.hardcore.page.googlecloud.PricingCalculatorResultPage;
import com.training.hardcore.util.TabSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class TenMinuteEmailHomePage extends AbstractPage {

    private static final String HOME_PAGE_URL = "https://10minutemail.com/";
    private final PricingCalculatorResultPage calculatorResultPage;
    private final By emailInputLocator = By.xpath("//input[@id = 'mail_address']");

    @FindBy(id = "copy_address")
    private WebElement copyEmailButton;

    public TenMinuteEmailHomePage(WebDriver driver, PricingCalculatorResultPage calculatorResultPage) {
        super(driver);
        this.calculatorResultPage = calculatorResultPage;
    }

    public TenMinuteEmailHomePage openPage() {
        driver.get(HOME_PAGE_URL);
        driver.manage().window().setSize(new Dimension(1600, 900));
        logger.info("Page with a temporary email box was opened. ");
        return this;
    }

    public TenMinuteEmailHomePage copyEmailFromBox() {
        new WebDriverWait(driver, 15).until((ExpectedCondition<Boolean>) driver ->
                Objects.requireNonNull(driver).findElement(emailInputLocator).getAttribute("value").length() != 0);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(copyEmailButton)).click();
        logger.info("Temporary email address was copied to the clipboard.");
        return this;
    }

    public PricingCalculatorResultPage switchToCalculatorResultPage() {
        TabSwitcher.switchBetweenTabs(driver, 0);
        return calculatorResultPage;
    }
}
