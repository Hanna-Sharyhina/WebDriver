package com.training.icanwin.page;

import com.training.icanwin.customconditions.PageLoadingIsCompletedCondition;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;


public class PastebinHomePage {
    private static final String HOME_PAGE_URL = "https://pastebin.com";
    private final WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement pasteCodeTextBox;

    @FindBy(xpath = "//span[@id = 'select2-postform-expiration-container']")
    private WebElement pasteExpirationForm;

    @FindBy(id = "postform-name")
    private WebElement pasteNameTextBox;

    @FindBy(xpath = "//button[@class='btn -big']")
    private WebElement submitNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOME_PAGE_URL);
        new WebDriverWait(driver, 10)
                .until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
        return this;
    }

    public PastebinHomePage addPasteCode(String pasteCode) {
        pasteCodeTextBox.sendKeys(pasteCode);
        return this;
    }

    public PastebinHomePage selectPasteExpiration(String pasteExpiration) {
        pasteExpirationForm.click();
        List<WebElement> menuItems = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("li")));
        Optional<WebElement> item = menuItems.stream()
                .filter(a -> a.getText().equalsIgnoreCase(pasteExpiration))
                .findAny();
        item.ifPresent(WebElement::click);
        return this;
    }

    public PastebinHomePage addPasteName(String pasteName) {
        pasteNameTextBox.sendKeys(pasteName);
        return this;
    }

    public PastebinResultPage createPaste() {
        submitNewPasteButton.click();
        return new PastebinResultPage(driver);
    }
}