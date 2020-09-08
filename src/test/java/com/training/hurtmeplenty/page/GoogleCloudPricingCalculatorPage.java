package com.training.hurtmeplenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorPage {

    private final WebDriver driver;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
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

    public GoogleCloudPricingCalculatorPage switchToInnerFrame() {
        driver.switchTo().frame(firstInnerFrame);
        driver.switchTo().frame(secondInnerFrame);
        return this;
    }

    public GoogleCloudPricingCalculatorPage activateComputeEngineSection() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(computeEngineSectionButton)).click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage addNumberOfInstances(String numberOfInstances) {
        numberOfInstancesInput.sendKeys(numberOfInstances);
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseInstanceType(String option) {
        selectElementFromDropDownMenu(instanceTypeMenu, "//div[contains(text(), '" + option + "')]");
        return this;
    }

    public GoogleCloudPricingCalculatorPage addGPUs() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//form[@name = 'ComputeEngineForm']//md-checkbox[@aria-label = 'Add GPUs']"))).click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseNumberOfGPUs(String numberOfGPUsOption) {
        selectElementFromDropDownMenu(numberOfGPUsMenu, "//div[@id = 'select_container_373']//md-option[@value = '"
                + numberOfGPUsOption + "']");
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseTypeOfGPU(String typeOfGPU) {
        selectElementFromDropDownMenu(typeOfGPUMenu, "//div[contains(text(),'" +
                typeOfGPU + "')]/ancestor::md-option");
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseLocalSSD(String localSSD) {
        selectElementFromDropDownMenu(localSSDMenu,
                "//div[@id = 'select_container_194']//div[contains(text(), '" + localSSD + "')]");
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseDataCenterLocation(String dataCenterLocation) {
        selectElementFromDropDownMenu(dataCenterLocationMenu,
                "//md-select-menu[@class = 'md-overflow']//div[contains(text(), '" + dataCenterLocation + "')]");
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseCommittedUsage(String usage) {
        selectElementFromDropDownMenu(committedUsageMenu,
                "//div[@id = 'select_container_96']//div[contains(text(), '" +
                        usage + "')]/ancestor::md-option");
        return this;
    }

    public GoogleCloudPricingCalculatorResultPage addToEstimate() {
        addToEstimateButton.click();
        return new GoogleCloudPricingCalculatorResultPage(driver);
    }

    private void selectElementFromDropDownMenu(WebElement element, String xpath) {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(element)).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(By.xpath(xpath))).click();
    }
}