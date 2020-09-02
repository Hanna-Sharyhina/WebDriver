package com.training.bringiton.page;

import com.training.bringiton.customcondition.PageIsLoadedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public class HomePage {

    private static final String HOME_PAGE_URL = "https://pastebin.com";
    private final WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement pasteCodeTextBox;

    @FindBy(xpath = "//span[@id = 'select2-postform-format-container']")
    private WebElement syntaxHighlightingForm;

    @FindBy(xpath = "//span[@id = 'select2-postform-expiration-container']")
    private WebElement pasteExpirationForm;

    @FindBy(id = "postform-name")
    private WebElement pasteNameTextBox;

    @FindBy(xpath = "//button[@class='btn -big']")
    private WebElement submitNewPasteButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage() {
        driver.get(HOME_PAGE_URL);
        new WebDriverWait(driver, 10)
                .until(PageIsLoadedCondition.jQueryAJAXsCompleted());
        return this;
    }

    public HomePage addPasteCode(String pasteCode) {
        pasteCodeTextBox.sendKeys(pasteCode);
        return this;
    }

    public HomePage selectSyntaxHighlighting(String syntaxHighlighting) {
        return selectItemFromDropDownMenu(syntaxHighlightingForm, syntaxHighlighting);
    }

    public HomePage selectPasteExpiration(String pasteExpiration) {
        return selectItemFromDropDownMenu(pasteExpirationForm, pasteExpiration);
    }

    private HomePage selectItemFromDropDownMenu(WebElement form, String selectingItem) {
        form.click();
        List<WebElement> menuItems = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("li")));
        Optional<WebElement> item = menuItems.stream()
                .filter(a -> a.getText().equalsIgnoreCase(selectingItem))
                .findAny();
        item.ifPresent(WebElement::click);
        return this;
    }

    public HomePage addPasteName(String pasteName) {
        pasteNameTextBox.sendKeys(pasteName);
        return this;
    }

    public ResultPage createPaste() {
        submitNewPasteButton.click();
        return new ResultPage(driver);
    }
}
