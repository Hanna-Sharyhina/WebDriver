package com.training.hurtmeplenty.test;

import com.training.hurtmeplenty.page.GoogleCloudHomePage;
import com.training.hurtmeplenty.page.GoogleCloudPricingCalculatorResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleCloudPricingCalculatorTest {

    private WebDriver driver;
    private GoogleCloudPricingCalculatorResultPage calculatorPage;
    private final String VM_CLASS = "Regular";
    private final String INSTANCE_TYPE = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private final String LOCAL_SSD = "2x375 GB";
    private final String DATA_CENTER_LOCATION = "Frankfurt (europe-west3)";
    private final String COMMITMENT_USAGE = "1 Year";
    private final String EXPECTED_TOTAL_COST = "1,082.77";


    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeClass(alwaysRun = true)
    public void fillAllOfRequiredFields() {
        String searchRequest = "Google Cloud Platform Pricing Calculator";
        String typeOfGPU = "NVIDIA Tesla V100";
        calculatorPage = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForTerms(searchRequest)
                .getToRequiredSearchResult(searchRequest)
                .switchToInnerFrame()
                .activateComputeEngineSection()
                .addNumberOfInstances("4")
                .chooseInstanceType(INSTANCE_TYPE)
                .addGPUs()
                .chooseNumberOfGPUs("1")
                .chooseTypeOfGPU(typeOfGPU)
                .chooseLocalSSD(LOCAL_SSD)
                .chooseDataCenterLocation(DATA_CENTER_LOCATION)
                .chooseCommittedUsage(COMMITMENT_USAGE)
                .addToEstimate();
    }

    @Test
    public void isVMClassContainsExpectedValue() {
        Assert.assertEquals(calculatorPage.getComputedMachineClassText(), VM_CLASS.toLowerCase());
    }

    @Test
    public void isInstanceTypeContainsExpectedValue() {
        Assert.assertEquals(calculatorPage.getComputedInstanceTypeText(), INSTANCE_TYPE.split(" ")[0]);
    }

    @Test
    public void isRegionContainsExpectedValue() {
        Assert.assertEquals(calculatorPage.getComputedRegionText(), DATA_CENTER_LOCATION.split(" ")[0]);
    }

    @Test
    public void isLocalSSDContainsExpectedValue() {
        Assert.assertEquals(calculatorPage.getComputedLocalSSDText(), LOCAL_SSD.split(" ")[0]);
    }

    @Test
    public void isCommitmentTermContainsExpectedValue() {
        Assert.assertEquals(calculatorPage.getCommitmentTermText(), COMMITMENT_USAGE);
    }

    @Test
    public void isTotalEstimatedCostEqualsToExpected() {
        Assert.assertEquals(calculatorPage.getTotalEstimatedCost(), EXPECTED_TOTAL_COST);
    }

    @AfterClass(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
