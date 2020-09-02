package com.training.icanwin.customconditions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PageLoadingIsCompletedCondition {

    public static ExpectedCondition<Boolean> jQueryAJAXsCompleted() {
        return driver -> (Boolean) ((JavascriptExecutor)
                driver).executeScript("return (window.jQuery !=0) && (jQuery.active == 0)");
    }
}
