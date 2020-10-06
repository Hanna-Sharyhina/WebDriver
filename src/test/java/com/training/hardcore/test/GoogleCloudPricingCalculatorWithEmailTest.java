package com.training.hardcore.test;

import com.training.hardcore.model.ComputeEngine;
import com.training.hardcore.page.googlecloud.GoogleCloudHomePage;
import com.training.hardcore.service.ComputeEngineCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GoogleCloudPricingCalculatorWithEmailTest extends CommonConditions {

    @Test
    public void isReceivedByEmailTotalCostEqualsToExpectedOne() {
        ComputeEngine testComputeEngine = ComputeEngineCreator.withParametersFromProperty();
        String searchRequest = "Google Cloud Platform Pricing Calculator";
        String totalCostFromEmail = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForTerms(searchRequest)
                .getToRequiredSearchResult(searchRequest)
                .switchToInnerFrame()
                .activateComputeEngineSection()
                .fillAllOfRequiredFields(testComputeEngine)
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
        assertThat(totalCostFromEmail, is(equalTo(testComputeEngine.getExpectedTotalCost())));
    }
}
