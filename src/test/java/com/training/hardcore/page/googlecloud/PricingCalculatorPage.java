package com.training.hardcore.page.googlecloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricingCalculatorPage {
    private final WebDriver driver;

    public PricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//iframe[@src = '/products/calculator/index_ad8ca20a6d1799e286a0c0839aeb86ca523afe927b04501d8ba77dc59e5b8523.frame']")
    private WebElement firstInnerFrame;

    @FindBy(xpath = "//iframe[@src = 'https://cloudpricingcalculator.appspot.com']")
    private WebElement secondInnerFrame;

    @FindBy(xpath = "//div[@title = 'Compute Engine' and @class = 'tab-holder compute']")
    private WebElement computeEngineSectionButton;

    @FindBy(xpath = "//input[@ng-model ='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//md-select[@placeholder = 'Instance type']")
    private WebElement instanceTypeMenu;

    @FindBy(xpath = "//md-select[@placeholder = 'Number of GPUs']")
    private WebElement numberOfGPUsMenu;

    @FindBy(xpath = "//md-select[@placeholder = 'GPU type']")
    private WebElement typeOfGPUMenu;

    @FindBy(xpath = "//md-select[@placeholder = 'Local SSD']")
    private WebElement localSSDMenu;

    @FindBy(xpath = "//md-select[@id = 'select_88']")
    private WebElement dataCenterLocationMenu;

    @FindBy(xpath = "//md-select[@id = 'select_95']")
    private WebElement committedUsageMenu;

    @FindBy(xpath = "//form[@name = 'ComputeEngineForm']//button[@aria-label = 'Add to Estimate']")
    private WebElement addToEstimateButton;

    public PricingCalculatorPage switchToInnerFrame() {
        driver.switchTo().frame(firstInnerFrame);
        driver.switchTo().frame(secondInnerFrame);
        return this;
    }

    public PricingCalculatorPage activateComputeEngineSection() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(computeEngineSectionButton)).click();
        return this;
    }

    public PricingCalculatorPage addNumberOfInstances(String numberOfInstances) {
        numberOfInstancesInput.sendKeys(numberOfInstances);
        return this;
    }

    public PricingCalculatorPage chooseInstanceType(String option) {
        selectElementFromDropDownMenu(instanceTypeMenu, "//div[contains(text(), '" + option + "')]");
        return this;
    }

    public PricingCalculatorPage addGPUs() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//form[@name = 'ComputeEngineForm']//md-checkbox[@aria-label = 'Add GPUs']"))).click();
        return this;
    }

    public PricingCalculatorPage chooseNumberOfGPUs(String numberOfGPUsOption) {
        selectElementFromDropDownMenu(numberOfGPUsMenu, "//div[@id = 'select_container_373']//md-option[@value = '"
                + numberOfGPUsOption + "']");
        return this;
    }

    public PricingCalculatorPage chooseTypeOfGPU(String typeOfGPU) {
        selectElementFromDropDownMenu(typeOfGPUMenu, "//div[contains(text(),'" +
                typeOfGPU + "')]/ancestor::md-option");
        return this;
    }

    public PricingCalculatorPage chooseLocalSSD(String localSSD) {
        selectElementFromDropDownMenu(localSSDMenu,
                "//div[@id = 'select_container_194']//div[contains(text(), '" + localSSD + "')]");
        return this;
    }

    public PricingCalculatorPage chooseDataCenterLocation(String dataCenterLocation) {
        selectElementFromDropDownMenu(dataCenterLocationMenu,
                "//md-select-menu[@class = 'md-overflow']//div[contains(text(), '" + dataCenterLocation + "')]");
        return this;
    }

    public PricingCalculatorPage chooseCommittedUsage(String usage) {
        selectElementFromDropDownMenu(committedUsageMenu,
                "//div[@id = 'select_container_96']//div[contains(text(), '" +
                        usage + "')]/ancestor::md-option");
        return this;
    }

    public PricingCalculatorResultPage addToEstimate() {
        addToEstimateButton.click();
        return new PricingCalculatorResultPage(driver);
    }

    private void selectElementFromDropDownMenu(WebElement element, String xpath) {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(element)).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(By.xpath(xpath))).click();
    }
}
