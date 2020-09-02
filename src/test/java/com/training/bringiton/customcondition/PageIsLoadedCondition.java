package com.training.bringiton.customcondition;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PageIsLoadedCondition {
    public static ExpectedCondition<Boolean> jQueryAJAXsCompleted() {
        return driver -> (Boolean) ((JavascriptExecutor)
                driver).executeScript("return (window.jQuery !=0) && (jQuery.active == 0)");
    }
}
