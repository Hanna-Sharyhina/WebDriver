package com.training.icanwin.test;

import com.training.icanwin.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PastebinTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void newPasteIsCreate() {
        WebElement successfulNoteElement = new PastebinHomePage(driver)
                .openPage()
                .addPasteCode("Hello from WebDriver")
                .selectPasteExpiration("10 Minutes")
                .addPasteName("helloweb")
                .createPaste()
                .getSuccessfulCreationNote();
        Assert.assertTrue(successfulNoteElement.isDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
