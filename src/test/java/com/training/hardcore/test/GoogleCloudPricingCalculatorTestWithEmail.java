package com.training.hardcore.test;

import com.training.hardcore.page.googlecloud.GoogleCloudHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleCloudPricingCalculatorTestWithEmail {

    private WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void isReceivedByEmailTotalCostEqualsToExpectedOne() {
        String searchRequest = "Google Cloud Platform Pricing Calculator";
        String expectedTotalCost = "1,082.77";
        String totalCostFromEmail = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForTerms(searchRequest)
                .getToRequiredSearchResult(searchRequest)
                .switchToInnerFrame()
                .activateComputeEngineSection()
                .addNumberOfInstances("4")
                .chooseInstanceType("n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .addGPUs()
                .chooseNumberOfGPUs("1")
                .chooseTypeOfGPU("NVIDIA Tesla V100")
                .chooseLocalSSD("2x375 GB")
                .chooseDataCenterLocation("Frankfurt (europe-west3)")
                .chooseCommittedUsage("1 Year")
                .addToEstimate()
                .openEmailYourEstimateWindow()
                .openPageWithTemporaryEmail()
                .openPage()
                .copyEmailFromBox()
                .switchToCalculatorResultPage()
                .switchToInnerFrame()
                .addEmailToEmailField()
                .sendEmail()
                .openReceivedEmail()
                .getTotalCostFromEmail();
        Assert.assertEquals(totalCostFromEmail, expectedTotalCost);
    }

    @AfterClass(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
