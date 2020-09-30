package com.training.hardcore.test;

import com.training.hardcore.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

public class CommonConditions {

    protected WebDriver driver;

    @BeforeTest()
    public void browserSetup() {
        driver = DriverSingleton.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void browserTearDown() {
        DriverSingleton.closeDriver();
    }
}
