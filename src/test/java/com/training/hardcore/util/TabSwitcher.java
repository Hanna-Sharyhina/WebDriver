package com.training.hardcore.util;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class TabSwitcher {

    public static void switchBetweenTabs(WebDriver driver, int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
    }
}
