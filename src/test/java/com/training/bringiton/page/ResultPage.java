package com.training.bringiton.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {
    private final WebDriver driver;
    private static final String XPATH_OF_HEAD_LINE = "//div[@class = 'info-top']/h1";
    private static final String XPATH_OF_SYNTAX_HIGHLIGHTING_BUTTON = "//div[@class = 'left']/a[@class = 'btn -small h_800']";
    private static final String XPATH_OF_CODE_BOX = "//div[@class = 'source']";

    @FindBy(xpath = XPATH_OF_HEAD_LINE)
    private WebElement resultPageHeadLine;

    @FindBy(xpath = XPATH_OF_SYNTAX_HIGHLIGHTING_BUTTON)
    private WebElement resultPageSyntaxHighlightingButton;

    @FindBy(xpath = XPATH_OF_CODE_BOX)
    private WebElement resultPageCodeBox;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getResultPageHeadLineText() {
        return getElementFromResultPage(XPATH_OF_HEAD_LINE);
    }

    public String getSyntaxHighlightingFromResultPage() {
        return getElementFromResultPage(XPATH_OF_SYNTAX_HIGHLIGHTING_BUTTON);
    }

    public String getPastedCodeFromResultPage() {
        return getElementFromResultPage(XPATH_OF_CODE_BOX);
    }

    public String getElementFromResultPage(String xpath) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText();
    }
}
