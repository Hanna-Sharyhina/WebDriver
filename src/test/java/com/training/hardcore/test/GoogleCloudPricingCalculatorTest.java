package com.training.hardcore.test;

import com.training.hardcore.model.ComputeEngine;
import com.training.hardcore.page.googlecloud.GoogleCloudHomePage;
import com.training.hardcore.service.ComputeEngineCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GoogleCloudPricingCalculatorTest extends CommonConditions {

    @Test
    public void isTotalEstimatedCostEqualsToExpected() {
        ComputeEngine testComputeEngine = ComputeEngineCreator.withParametersFromProperty();
        String searchRequest = "Google Cloud Platform Pricing Calculator";
        String totalEstimatedCost = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForTerms(searchRequest)
                .getToRequiredSearchResult(searchRequest)
                .switchToInnerFrame()
                .activateComputeEngineSection()
                .fillAllOfRequiredFields(testComputeEngine)
                .addToEstimate()
                .getTotalEstimatedCost();
        assertThat(totalEstimatedCost, is(equalTo(testComputeEngine.getExpectedTotalCost())));
    }
}
