package com.training.hurtmeplenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorResultPage {

    private final WebDriver driver;

    public GoogleCloudPricingCalculatorResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//b[contains(text(),'Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    public String getComputedMachineClassText() {
        return getEstimatedValue("VM class", " ", 2);
    }

    public String getComputedInstanceTypeText() {
        return getEstimatedValue("Instance type", " ", 2);
    }

    public String getComputedRegionText() {
        return getEstimatedValue("Region", ":", 1).trim();
    }

    public String getComputedLocalSSDText() {
        return getEstimatedValue("local SSD", " ", 5);
    }

    public String getCommitmentTermText() {
        return getEstimatedValue("Commitment term", ":", 1).trim();
    }

    public String getTotalEstimatedCost() {
        return totalEstimatedCost.getText().split(" ")[4];
    }


    private String getEstimatedValue(String containsPart, String regex, int index) {
        String defaultLocator = "//md-list-item/div[contains(text(), '%s')]";
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By
                                .xpath(String.format(defaultLocator, containsPart))))
                .getText().split(regex)[index].trim();
    }
}
